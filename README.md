# codeGen

此项目的目的为基于数据表，生成 PO、VO、JPA Repository、Service、Controller、UT。

原本计划完成数据表解析到模板渲染的全过程，后面发现 Mybatis-Plus Generator 足以满足要求，所以改用 Mybatis-Plus Generator 实现。
Mybatis-Plus Generator 在设置包名时，没有考虑为 "" 和 null 的情况，所以扩展了 AutoGenerator 和 ConfigBuilder 来处理。 除此以外，Mybatis-Plus Generator 
完美地满足了我的需求。
我的工作为编写了适用于单表的 Controller, Service, Service Impl, JPA Repository, PO, VO 的 velocity 模板。

运行 CodeGenerator 类即可启用此项目。