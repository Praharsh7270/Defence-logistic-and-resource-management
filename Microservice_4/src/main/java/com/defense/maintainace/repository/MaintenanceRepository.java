package com.defense.maintainace.repository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MaintenanceRepository {
 private final JdbcTemplate jdbc;
 public MaintenanceRepository(JdbcTemplate jdbc){this.jdbc=jdbc;}
 public boolean enabledUserExists(long id){
  Integer n=jdbc.queryForObject("SELECT COUNT(*) FROM users WHERE id=? AND enabled=TRUE",Integer.class,id);
  return n!=null && n>0;
 }
 public boolean resourceExists(String type,long id){
  String table=type.equals("VEHICLE") ? "vehicles" : "equipment";
  Integer n=jdbc.queryForObject("SELECT COUNT(*) FROM "+table+" WHERE id=?",Integer.class,id);
  return n!=null && n>0;
 }
 public long create(long uid,String type,long resourceId,String mt,String description,String priority){
  Long id=jdbc.queryForObject("INSERT INTO maintenance_requests(request_number,requester_id,resource_type,resource_id,maintenance_type,description,priority,status) VALUES(?,?,?,?,?,?,?,'SUBMITTED') RETURNING id",
   Long.class,"MNT-"+java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase(),uid,type,resourceId,mt,description,priority);
  addHistory(id,uid,null,"SUBMITTED","Maintenance request submitted");
  return id;
 }
 public List<Map<String,Object>> myRequests(long uid){return jdbc.queryForList("SELECT * FROM maintenance_requests WHERE requester_id=? ORDER BY created_at DESC",uid);}
 public List<Map<String,Object>> allRequests(){return jdbc.queryForList("SELECT * FROM maintenance_requests ORDER BY created_at DESC");}
 public Map<String,Object> find(long id){return jdbc.queryForMap("SELECT * FROM maintenance_requests WHERE id=?",id);}
 public String status(long id){return jdbc.queryForObject("SELECT status FROM maintenance_requests WHERE id=?",String.class,id);}
 public void review(long id,long reviewer,String status,String note){
  jdbc.update("UPDATE maintenance_requests SET status=?,assigned_to=COALESCE(assigned_to,?),review_note=?,updated_at=CURRENT_TIMESTAMP WHERE id=?",status,reviewer,note,id);
 }
 public void updateWorkStatus(long id,String status,LocalDate scheduled,LocalDate completed,String note){
  jdbc.update("UPDATE maintenance_requests SET status=?,scheduled_date=COALESCE(?,scheduled_date),completed_date=COALESCE(?,completed_date),review_note=COALESCE(?,review_note),updated_at=CURRENT_TIMESTAMP WHERE id=?",
   status,scheduled==null?null:Date.valueOf(scheduled),completed==null?null:Date.valueOf(completed),note,id);
 }
 public void addHistory(long id,Long uid,String oldS,String newS,String note){
  jdbc.update("INSERT INTO maintenance_request_history(maintenance_request_id,changed_by,old_status,new_status,remarks) VALUES(?,?,?,?,?)",id,uid,oldS,newS,note);
 }
 public List<Map<String,Object>> history(long id){return jdbc.queryForList("SELECT * FROM maintenance_request_history WHERE maintenance_request_id=? ORDER BY changed_at,id",id);}
 public List<Map<String,Object>> overdue(){return jdbc.queryForList("SELECT * FROM maintenance_requests WHERE scheduled_date<CURRENT_DATE AND status IN ('APPROVED','SCHEDULED','IN_PROGRESS') ORDER BY scheduled_date");}
}
