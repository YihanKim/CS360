<?php
	#result2.php

	/** Implement the function 'find_3PCs'
		- Explanation: Return at most 3 PCs whose prices are closet to the inputted price.
		- Input: db connection info($conn), a price($price)
		- Output: an array of the specifications of 3PCs (that is, the maker, model number, ram, hd, and price)
				For example, the form of output value is
				array(
				0 => array(
						"MAKER" => "A",
						"MODEL" => 1001,
						"RAM" => 1024,
						"HD" => 250,
						"PRICE" => 2114
					),
				1 => array(
						"MAKER" => "A",
						"MODEL" => 1002,
						"RAM" => 512,
						"HD" => 250,
						"PRICE" => 995
					)
				);
	*/
	function find_3PCs($conn,$price){

		//implement..
		$queryResult = array();
		$result = $conn->query("select * from pc");
		while ($tuple = $result->fetchRow()) {
			array_push($tuple, abs($tuple[4] - $price));
			array_push($queryResult, $tuple);
		};

		function sortByOrder($a, $b) {
    	return $a[5] - $b[5];
		}
		usort($queryResult, 'sortByOrder');

		if (count($queryResult) > 3) {
			$queryResult = array_slice($queryResult, 0, 3);
		}

		foreach($queryResult as $idx => $tuple) {
			$maker = $conn->query("select maker from product where model=".$tuple[0])->fetchRow()[0];
			$queryResult[$idx] = array(
				"MAKER" => $maker,
				"MODEL" => $tuple[0],
				"RAM" => $tuple[2],
				"HD" => $tuple[3],
				"PRICE" => $tuple[4]
			);
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

			/* Implement an ouput screen*/

			echo '<p>가격 $';
			echo $_GET['price'];
			echo '에 가장 가까운 PC 3대는 아래와 같습니다.</p><br>';

			$res = find_3PCs($conn, $_GET['price']);

			echo '<table border="1">';
			echo '<tr><th>Maker</th><th>Model</th><th>RAM</th><th>HD</th><th>Price</th></tr>';

			foreach($res as $idx => $tuple) {
				echo "<tr>";
				//echo "<th>".(string)($idx + 1)."</th>";
				foreach($tuple as $key=>$value) {
					echo "<th>".$value."</th>";
				}
				echo "</tr>";
			};

			/* end of implement */

			$conn->disconnect();
		}
		include('../includes/footer.html');
	}
?>
