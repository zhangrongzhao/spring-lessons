<%--
  Created by IntelliJ IDEA.
  User: allcure
  Date: 2020/8/5
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .align-center{
            margin:0 auto;      /* 居中 这个是必须的，，其它的属性非必须 */
            width:500px;        /* 给个宽度 顶到浏览器的两边就看不出居中效果了 */
            background:red;     /* 背景色 */
            text-align:center;  /* 文字等内容居中 */
        }
    </style>
</head>
<body>
      <div class="align-center" style="height: 500px;width:500px;">

          <h1>\${userObject.name} : ${userObject.name}</h1>
          <h1>\${applicationScope["scopedTarget.user"].name} : ${applicationScope["scopedTarget.user"].name}</h1>

      </div>
</body>
</html>
