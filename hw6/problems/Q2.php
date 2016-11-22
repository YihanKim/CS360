<?php
	$page_title = 'CS360 HW6 / '.basename(__FILE__);
	include('../includes/header.html');
?>
<p>Find at most 3 PCs whose prices are closet to the desired price.</p>
<form action="result2.php" method="get">
	<!--Implement an input form -->
	가격을 입력하세요: <input type="text" name="price">
	<input type="submit" value="제출">
	<!-- end of implement -->
</form>
<?php
	include('../includes/footer.html');
?>
