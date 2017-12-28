# Labour
四娘的员工劳动力统计项目

使用java语言开发，开发工具为eclipse oxygen
项目使用基本的mvc模式，即model，view，controller和service层

后台技术使用spring和hibernate，使用hibernate.cfg.xml作为数据库配置文件，applicationContext作为spring容器，并初始化类对象
数据库为mySQL，编码方式UTF-8
前端技术为swing和awt，使用windowbuild插件完成可视化开发，使用listener监听器模式

从功能上来说，主要包括三个模块：员工管理、茶叶管理和劳务管理。
员工和茶叶管理，包括对员工和茶叶的添加、删除和查找操作，查找包括基本的分页。
劳务管理也包括劳务的添加、删除和查询，其中查询实现了基本的分页；为了使用的方便和开发的简单，添加操作使用查询+根据查询的id填写的方式。
