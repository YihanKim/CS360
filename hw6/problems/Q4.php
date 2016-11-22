<?php
	$page_title = 'CS360 HW6 / '.basename(__FILE__);
	include('../includes/header.html');
?>
<p>Find the cheapest "system" (PC plus printer or Laptop plus printer) that is within the "budget" (total price of a PC (or a Laptop) and printer), and minimum speed.</p>
<p>Make the printer a color printer (color = 1) if possible.</p>
<p><b>No blanks are allowed.</b></p>
<form action="result4.php" method="get">
	<!--Implement an input form -->
	예산(Budget): <input type="text" name="budget"><br>
	최소사양(minimum speed): <input type="text" name="speed"><br>
	<input type="submit" value="제출">

	<!-- end of implement -->
</form>
<?php
	include('../includes/footer.html');
?>
