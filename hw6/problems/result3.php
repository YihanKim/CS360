<?php
	#result3.php

	/** Implement the function 'insert_Laptop'
		- Explanation: insert laptop info into tables Product and PC if there is no Laptop with that model number.
		- Input: db connection info($conn), laptop info($maker,$model,$speed,$ram,$hd,$screen,$price)
		- Output: true if the insertion is success, false otherwise
	*/
	function insert_Laptop($conn,$maker,$model,$speed,$ram,$hd,$screen,$price){
		$queryResult = true;

		//implement..

		$check1 = $conn->query("select count(*) from product where model = ".$model);
		$check2 = $conn->query("select count(*) from laptop where model = ".$model);

		if ($check1->fetchRow()[0] || $check2->fetchRow()[0]) $queryResult = false;
		else {

			$query1 = $conn->prepare("insert into product values (?,?,'laptop')");
			$args = array($maker, $model);
			$result = $conn->execute($query1, $args);
			if (PEAR::isError($result)) {
				die($result->getMessage());
			}

			$query2 = $conn->prepare("insert into laptop values (?,?,?,?,?,?)");
			$args = array($model, $speed, $ram, $hd, $screen, $price);
			$result = $conn->execute($query2, $args);
			if (PEAR::isError($result)) {
				die($result->getMessage());
			}
		}
		// end of implement

		return $queryResult;
	}
?>
<?php
	if(!isset($validPrint)){
		$page_title = 'CS360 HW6 / '.basename(__FILE__);
		include('../includes/header.html');
		include('../Config/db.connect.php');
		if (!PEAR::isError($conn)){

			/* Implement an output screen */

			$result = insert_laptop($conn, $_GET['maker'], $_GET['model'], $_GET['speed'], $_GET['ram'], $_GET['hd'], $_GET['screen'], $_GET['price']);
			if ($result) {
				echo "노트북 튜플 (".$_GET['model'].", ".$_GET['speed'].", ".$_GET['ram'].", ".$_GET['hd'].", ".$_GET['screen'].", ".$_GET['price'].") 이 추가되었습니다";

			} else {
				echo "노트북 튜플 (".$_GET['model'].", ".$_GET['speed'].", ".$_GET['ram'].", ".$_GET['hd'].", ".$_GET['screen'].", ".$_GET['price'].") 은 추가될 수 없습니다.<br>";
				echo "모델번호".(string)$_GET["model"]."을(를) 가진 제품이 이미 존재합니다.";
			}

			/* end of implement */

			$conn->disconnect();
		}
		include('../includes/footer.html');
	}
?>
