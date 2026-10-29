-- Demo seed data for the Defense Logistics database.
-- Intended for Spring Boot's data.sql initialization.
-- Safe to run repeatedly: natural-key conflicts are ignored.
-- DEMO ONLY: password_hash values below are placeholders, not usable passwords.

INSERT INTO users (username, password_hash, role, enabled)
VALUES
  ('demo.admin', 'hello', 'ADMIN', TRUE),
  ('demo.officer', 'world', 'LOGISTICS_OFFICER', TRUE),
  ('demo.requester', 'to', 'REQUESTER', TRUE)
ON CONFLICT (username) DO NOTHING;

INSERT INTO personnel
    (employee_code, full_name, role, unit_name, posting, availability_status, active)
VALUES
  ('DEMO-PER-001', 'Arun Kumar', 'Driver', 'Transport Unit', 'Chennai', 'AVAILABLE', TRUE),
  ('DEMO-PER-002', 'Meera Nair', 'Technician', 'Maintenance Unit', 'Vellore', 'AVAILABLE', TRUE)
ON CONFLICT (employee_code) DO NOTHING;

INSERT INTO vehicles
    (registration_no, vehicle_type, driver_id, location, status, condition_status)
VALUES
  ('DEMO-VEH-001', 'Utility Truck',
   (SELECT id FROM personnel WHERE employee_code = 'DEMO-PER-001'),
   'Vellore', 'AVAILABLE', 'OPERATIONAL'),
  ('DEMO-VEH-002', 'Transport Van',
   (SELECT id FROM personnel WHERE employee_code = 'DEMO-PER-001'),
   'Chennai', 'AVAILABLE', 'NEEDS_REPAIR')
ON CONFLICT (registration_no) DO NOTHING;

INSERT INTO equipment
    (asset_code, name, category, location, ownership_unit, status, condition_status)
VALUES
  ('DEMO-EQP-001', 'Portable Generator', 'Power', 'Vellore', 'Maintenance Unit', 'AVAILABLE', 'OPERATIONAL'),
  ('DEMO-EQP-002', 'Field Radio', 'Communications', 'Chennai', 'Signals Unit', 'AVAILABLE', 'NEEDS_REPAIR')
ON CONFLICT (asset_code) DO NOTHING;

INSERT INTO logistics_requests
    (request_number, requested_by, requesting_unit, purpose, status)
VALUES
  ('DEMO-LOG-001',
   (SELECT id FROM users WHERE username = 'demo.requester'),
   'Transport Unit', 'Routine transport support for field operations', 'PENDING')
ON CONFLICT (request_number) DO NOTHING;

INSERT INTO request_items (request_id, resource_type, quantity, description)
SELECT lr.id, 'VEHICLE', 1, 'One utility vehicle required'
FROM logistics_requests lr
WHERE lr.request_number = 'DEMO-LOG-001'
  AND NOT EXISTS (
    SELECT 1 FROM request_items ri
    WHERE ri.request_id = lr.id AND ri.description = 'One utility vehicle required'
  );

INSERT INTO request_history (request_id, changed_by, old_status, new_status, remarks)
SELECT lr.id, u.id, NULL, 'PENDING', 'Demo logistics request created'
FROM logistics_requests lr
CROSS JOIN users u
WHERE lr.request_number = 'DEMO-LOG-001'
  AND u.username = 'demo.requester'
  AND NOT EXISTS (
    SELECT 1 FROM request_history rh
    WHERE rh.request_id = lr.id AND rh.remarks = 'Demo logistics request created'
  );

INSERT INTO inventory (item_code, item_name, unit, quantity, reorder_level)
VALUES
  ('DEMO-INV-001', 'Engine Oil', 'litre', 40, 10),
  ('DEMO-INV-002', 'Air Filter', 'piece', 12, 4),
  ('DEMO-INV-003', 'First Aid Kit', 'kit', 8, 2)
ON CONFLICT (item_code) DO NOTHING;

-- A demo maintenance request lets you immediately test GET endpoints.
-- The resource ID is selected from the seeded vehicle rather than assumed to be 1.
INSERT INTO maintenance_requests
    (request_number, requester_id, resource_type, resource_id, maintenance_type,
     description, priority, status)
SELECT
    'DEMO-MNT-001', u.id, 'VEHICLE', v.id, 'PREVENTIVE',
    'Routine engine inspection', 'NORMAL', 'SUBMITTED'
FROM users u
CROSS JOIN vehicles v
WHERE u.username = 'demo.requester'
  AND v.registration_no = 'DEMO-VEH-001'
ON CONFLICT (request_number) DO NOTHING;

INSERT INTO maintenance_request_history
    (maintenance_request_id, changed_by, old_status, new_status, remarks)
SELECT mr.id, u.id, NULL, 'SUBMITTED', 'Demo maintenance request created'
FROM maintenance_requests mr
CROSS JOIN users u
WHERE mr.request_number = 'DEMO-MNT-001'
  AND u.username = 'demo.requester'
  AND NOT EXISTS (
    SELECT 1 FROM maintenance_request_history mh
    WHERE mh.maintenance_request_id = mr.id
      AND mh.remarks = 'Demo maintenance request created'
  );

-- Seed one scheduled maintenance record in the legacy maintenance_records table.
INSERT INTO maintenance_records
    (resource_type, resource_id, maintenance_type, description, scheduled_date, status)
SELECT 'VEHICLE', v.id, 'PREVENTIVE', 'Demo scheduled vehicle inspection',
       CURRENT_DATE + 7, 'SCHEDULED'
FROM vehicles v
WHERE v.registration_no = 'DEMO-VEH-001'
  AND NOT EXISTS (
    SELECT 1 FROM maintenance_records m
    WHERE m.resource_type = 'VEHICLE'
      AND m.resource_id = v.id
      AND m.description = 'Demo scheduled vehicle inspection'
  );
