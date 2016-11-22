<?php
	$page_title = 'CS360 HW6 / '.basename(__FILE__);
	include('../includes/header.html');
?>
<p>Insert new Laptop information into tables Product and Laptop if there is no Laptop with that model number.</p>
<p><b>No blanks are allowed.</b></p>
<form action="result3.php" method="get">
	<!--Implement an input form -->
	<!--
	html 수준에서 SQL 입력을 제한하고 있습니다.
	다만 html 버전이 5 이하일 경우 정상적으로 작동하는지는 확인하지 않았습니다.	
	-->
	제조사(Manufacturer): <input type="text" name="maker" required><br>
	제품 번호(Model): <input type="number" name="model" step='1' required><br>
	CPU 속도(Speed): <input type="number" name="speed" step='0.01' required><br>
	메모리 용량(RAM): <input type="number" name="ram" step='1' required><br>
	저장장치 용량(Hard disk Size): <input type="number" name="hd" step='1'required><br>
	화면 크기(Screen Size): <input type="number" step='0.1' name="screen" required><br>
	가격(Price): <input type="number" name="price" step='1' required><br>
	<input type="submit" value="제출">
	<!-- end of implement -->
</form>
<?php
	include('../includes/footer.html');
?>
