<html>
<head>
<script type="text/javascript">
  console.log("test");
  var httpRequest = new XMLHttpRequest();//第一步：建立所需的对象
  httpRequest.open('GET', '/servlet/simpleAsync', true);//第二步：打开连接  将请求参数写在url中  ps:"./Ptest.php?name=test&nameone=testone"
  httpRequest.send();//第三步：发送请求  将请求参数写在URL中
  /**
   * 获取数据后的处理程序
   */
  httpRequest.onreadystatechange = function () {
      if (httpRequest.readyState == 4 && httpRequest.status == 200) {
          var json = httpRequest.responseText;//获取到json字符串，还需解析
          console.log(json);
      }
  };


</script>
</head>
<script type="text/javascript" src="./js/index.js"></script>
<body>

<h2>this is get request</h2>
<form action="/servlet/simpleAsync" method="get">
<div>
<input type="submit" value="submit">
</div>
</form>
</body>
</html>
