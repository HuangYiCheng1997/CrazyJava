# JavaWeb动态项目的目录结构



## 目录结构

```
|-- webapp                         # 站点根目录
    |--html,css,js image 可以被外界直接访问
    |--WEB-INF 浏览器无法访问的目录 
    	|--lib 存放web依赖的jar文件
    	|--classes 存放当前web项目源码生成的class文件
    	|--web.xml web项目配置文件(必须的)
```

