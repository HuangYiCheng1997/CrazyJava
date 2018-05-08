# Servlet

## What are Servlets ？

什么是Servlets？

Java Servlets are programs that run on a Web or Application server and act as a middle layer between a requests coming from a Web browser or other HTTP client and databases or applications on the HTTP server.

Java Servlets 是一个运行在Web服务器或Application服务器上的程序，是浏览器或HTTP客户端与HTTP服务器上的数据库或应用进行交互的中间人。

Servlets are Java classes which service HTTP requests and implement the **javax.servlet.Servlet** interface. Web application developers typically write servlets that extend javax.servlet.http.HttpServlet, an abstract class that implements the Servlet interface and is specially designed to handle HTTP requests. 

## Servlets Architecture

下图向你展示Servlets在Web应用中的位置。

![](https://www.tutorialspoint.com/servlets/images/servlet-arch.jpg)

## Servlets Tasks

Servlets perform the following major tasks.

Servlets 执行 以下 主要任务。

1. Read the explicit（显式的） data sent by the clients . This includes an HTML form on a Web page or it could also come from an applet or a custom HTTP client program. 
2. Read the implicit（隐式的）data sent by the clients.This includes cookies, media types and compression schemes the browser understands, and so forth. 
3. Process the data and generate the results. This process may require talking to a database, executing an RMI or CORBA call, invoking a Web service, or computing the response directly. 
4. Send the explicit data (i.e., the document) to the clients (browsers). This document can be sent in a variety of formats, including text (HTML or XML), binary (GIF images), Excel, etc. 
5. Send the implicit HTTP response to the clients (browsers). This includes telling the browsers or other clients what type of document is being returned (e.g., HTML), setting cookies and caching parameters, and other such tasks. 