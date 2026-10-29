package com.defense.maintainace.service;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.defense.maintainace.dto.CreateMaintenanceRequest;
import com.defense.maintainace.dto.ReviewMaintenanceRequest;
import com.defense.maintainace.dto.UpdateMaintenanceStatusRequest;
import com.defense.maintainace.repository.MaintenanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
@Service
public class MaintenanceService {
 private final MaintenanceRepository repo;
 public MaintenanceService(MaintenanceRepository repo){this.repo=repo;}
 public List<Map<String,Object>> myRequests(long uid){ensureUser(uid);return repo.myRequests(uid);}
 public List<Map<String,Object>> allRequests(){return repo.allRequests();}
 public List<Map<String,Object>> overdue(){return repo.overdue();}
 public Map<String,Object> get(long id){return repo.find(id);}
 public List<Map<String,Object>> history(long id){repo.find(id);return repo.history(id);}
 @Transactional public Map<String,Object> create(CreateMaintenanceRequest r){
  ensureUser(r.requesterId());
  String type=r.resourceType().trim().toUpperCase(), mt=r.maintenanceType().trim().toUpperCase(), p=r.priority().trim().toUpperCase();
  if(!repo.resourceExists(type,r.resourceId()))throw error(HttpStatus.NOT_FOUND,type+" resource not found");
  long id=repo.create(r.requesterId(),type,r.resourceId(),mt,r.description().trim(),p);
  return Map.of("requestId",id,"status","SUBMITTED","message","Maintenance request submitted");
 }
 @Transactional public Map<String,Object> review(long id,ReviewMaintenanceRequest r){
  ensureUser(r.reviewerId());String old=repo.status(id), decision=r.decision().trim().toUpperCase();
  if(!old.equals("SUBMITTED"))throw error(HttpStatus.CONFLICT,"Only SUBMITTED requests can be reviewed");
  if(!Set.of("APPROVED","REJECTED").contains(decision))throw error(HttpStatus.BAD_REQUEST,"Decision must be APPROVED or REJECTED");
  repo.review(id,r.reviewerId(),decision,r.note());repo.addHistory(id,r.reviewerId(),old,decision,r.note());
  return Map.of("requestId",id,"status",decision);
 }
 @Transactional public Map<String,Object> updateStatus(long id,UpdateMaintenanceStatusRequest r){
  ensureUser(r.changedBy());String old=repo.status(id), next=r.status().trim().toUpperCase();
  if(!Set.of("SCHEDULED","IN_PROGRESS","COMPLETED","CANCELLED").contains(next))throw error(HttpStatus.BAD_REQUEST,"Invalid status");
  if(Set.of("REJECTED","CANCELLED","COMPLETED").contains(old))throw error(HttpStatus.CONFLICT,"This request is already closed");
  if(next.equals("COMPLETED")&&r.completedDate()==null)throw error(HttpStatus.BAD_REQUEST,"completedDate is required for completion");
  repo.updateWorkStatus(id,next,r.scheduledDate(),r.completedDate(),r.note());repo.addHistory(id,r.changedBy(),old,next,r.note());
  return Map.of("requestId",id,"status",next);
 }
 private void ensureUser(long id){if(!repo.enabledUserExists(id))throw error(HttpStatus.NOT_FOUND,"Enabled user not found");}
 private ResponseStatusException error(HttpStatus s,String m){return new ResponseStatusException(s,m);}
}
