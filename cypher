查询所有AccidentCase 的cypher
match (n:AccidentCase) return id(n) as accidentId,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10;

根据caseId查询案件信息
match (n:AccidentCase) where n.caseId ='"+caseId+"' return  id(n) as accidentId,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag

查询所有Customer实体
match (n:Customer) return id(n) as customerId,n.customerId as custId,n.customerName as custName limit 10

根据custId 获取客户信息
match (n:Customer) where n.customerId="410826196706160024" return id(n) as customerId,n.customerId as custId,n.customerName as custName limit 10

获取所有Hospital实体
match (n:Hospital) return id(n)as hospitalId,n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName limit 10

根据hospitalId获取hospital实体
match (n:Hospital)where n.hospitalId='3702020001' return id(n)as hospitalId,n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName

获取所有Employee实体
match (n:Employee) return id(n) as employeeId ,n.empId as empId limit 10

根据EmpolyeeId获取Employee实体
match (n:Employee)where n.empId="TAYU0090" return id(n) as employeeId ,n.empId as empId limit 10

获取所有Telephone实体
match (n:Telephone) return id(n) as telphoneId,n.telId as telId limit 10

根据telId查询telephone实体
match (n:Telephone)where n.telId ="15935421879"  return id(n) as telphoneId,n.telId as telId limit 10

查询 是投保人 关系的数量
match p=(n:Customer)-[r:是投保人]->(n1:AccidentCase) return count(p)

查询 是报案人 关系的数量
match p=(n:Customer)-[r:是报案人]->(n1:AccidentCase) return count(p)

查询 是领款人 关系的数量
match p=(n:Customer)-[r:是领款人]->(n1:AccidentCase) return count(p)

查询 是被保人 关系的数量
match p=(n:Customer)-[r:是被保人]->(n1:AccidentCase) return count(p)

查询所有客户和案件的报案关系
match (n:Customer)-[r:是报案人]->(n1:AccidentCase) return id(n) as sourceId,n.customerId as custId,n.customerName as custName, id(n1) as targetId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,type(r) as relation limit 10;

查询所有 是报案电话 的关系
match (tel:Telephone)-[r:是报案电话]->(acc:AccidentCase) return id(tel) as telephoneId,id(acc) as accidentId,type(r) as relation ,acc.orgno as orgno,acc.pfmoney as pfmoney,acc.caseId as caseId,acc.qzflag as qzflag,tel.telId as telId limit 10;

根据 telephoneId 获取 是报案电话关系
match (tel:Telephone)-[r:是报案电话]->(acc:AccidentCase) where tel.telId return id(tel) as telephoneId,id(acc) as accidentId,type(r) as relation ,acc.orgno as orgno,acc.pfmoney as pfmoney,acc.caseId as caseId,acc.qzflag as qzflag,tel.telId as telId limit 10;

获取所有 是被保人 关系
match (cu:Customer)-[r:是被保人]->(ac:AccidentCase) return id(cu) as customerId ,id(ac) as accidentId,type(r) as relation ,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10

根据customerid 获取是被保人关系的
match (cu:Customer)-[r:是被保人]->(ac:AccidentCase)where cu.customerId="" return id(cu) as customerId ,id(ac) as accidentId,type(r) as relation ,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10；

查询所有的· 业务归属于关系
match (n:AccidentCase)-[r:业务归属于]-(n1:Employee) return id(n) as accidentId,id(n1) as employeeId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag,n1.empId as empId limit 10;

根据业务员Id 获取 业务归属于关系
match (n:AccidentCase)-[r:业务归属于]-(n1:Employee) where n1.empId="" return id(n) as accidentId,id(n1) as employeeId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag,n1.empId as empId limit 10;

得到所有的 是领款人的关系
match (cu:Customer)-[r:是领款人]->(ac:AccidentCase) return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10

得到所有 是领款电话 的关系
match (t:Telephone)-[r:是领款电话]->(A:AccidentCase) return id(t) as telephoneId,id(A) as accidentId ,type(r) as relation,t.telId as telId,A.orgno as orgno,A.pfmoney as pfmoney,A.caseId as caseId,A.qzflag as qzflag limit 10;

//得到所有 治疗于 的关系
match (n:AccidentCase)-[r:治疗于]->(n1:Hospital) return id(n1)as hospitalId,n1.hospitaLevel as level,n1.hospitalId as hosId,n1.hospitalName as hosName,id(n) as accidentId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10;

得到所有 是投保人 关系
match (cu:Customer)-[r:是投保人]->(ac:AccidentCase) return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10

获取所有 使用 的关系
match (cu:Customer)-[r:使用]->(t:Telephone) return id(cu) as customerId,id(t) as telephoneId,type(r) as relation,t.telId as telId,cu.customerId as custId,cu.customerName as custName limit 10