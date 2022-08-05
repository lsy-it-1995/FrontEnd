# 1.显示所有员工的姓名，部门号和部门名称。
select e.last_name,  d.department_id, d.department_name
from employees e left join departments d
on e.department_id = d.department_id;

# 2.查询90号部门员工的job_id和90号部门的location_id
select e.job_id, d.location_id
from employees e join departments d
on e.department_id = d.department_id
where e.department_id = 90;

# 3.选择所有有奖金的员工的 last_name , department_name , location_id , city
select e.last_name, d.department_name, l.location_id, l.city
from employees e left outer join departments d 
on e.department_id = d.department_id
left outer join  locations l
on d.location_id = l.location_id
where e.commission_pct IS NOT NULL;

# 4.选择city在Toronto工作的员工的 last_name , job_id , department_id , department_name
select e.last_name, e.job_id, e.department_id, d.department_name
from employees e join departments d
on e.department_id = d.department_id
join locations l
on l.location_id = d.location_id
where l.city = 'Toronto';

# 5.查询员工所在的部门名称、部门地址、姓名、工作、工资，其中员工所在部门的部门名称为’Executive’
select d.department_name, l.street_address, e.last_name, e.job_id, e.salary
from employees e join departments d
on e.department_id = d.department_id
join locations l
on l.location_id = d.location_id
where department_name = 'Executive';

# 6.选择指定员工的姓名，员工号，以及他的管理者的姓名和员工号，结果类似于下面的格式
-- employees Emp# manager Mgr#
-- kochhar 101 king 100
select emp.last_name employees, emp.employee_id "Emp#", mgr.last_name manager, mgr.employee_id "Mgr#"
from employees emp left outer join employees mgr
on emp.employee_id = mgr.employee_id;

# 7.查询哪些部门没有员工
select d.department_id
from departments d left join employees e
on d.department_id = e.department_id
where e.department_id is NULL;

select d.department_id
from departments d
where not exists(
	select *
    from employees e
    where e.department_id = d.department_id
);
# 8. 查询哪个城市没有部门
select l.location_id, l.city
from locations l left join departments d
on l.location_id = d.location_id
where d.location_id is null;

# 9. 查询部门名为 Sales 或 IT 的员工信息
select e.last_name, e.employee_id, d.department_id
from employees e join departments d
on d.department_id  = e.department_id
where d.department_name in ('IT', 'Sales');
