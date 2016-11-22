<?php
	#result4.php

	/** Implement the function 'find_system'
		- Explanation: Return the infomation (all the attributes) of the cheapest system.
		- Input: db connection info($conn), system info($budget,$speed)
		- Output: an array of the system
			For example, the form of output value is
				array(
					"PC" => array(
						"MODEL" => 1007,
						"SPEED" => 2.20,
						"RAM" => 1024,
						"HD" => 200,
						"PRICE" => 510
					),
					"Printer" => array(
						"MODEL" => 3003,
						"color" => 1,
						"type" => 'laser',
						"PRICE" => 899
					)
				);
			or
				array(
					"Laptop" => array(
						"MODEL" => 2003,
						"SPEED" => 1.80,
						"RAM" => 512,
						"HD" => 60,
						"SCREEN" => 15.4,
						"PRICE" => 549
					),
					"Printer" => array(
						"MODEL" => 3003,
						"color" => 1,
						"type" => 'laser',
						"PRICE" => 899
					)
				);
	*/
	function find_system($conn,$budget,$speed){
		$queryResult = array();

		//implement..
		// 먼저 최소조건을 만족하는 pc/laptop와 printer 배열을 만든다.
		$conn->setFetchMode(DB_FETCHMODE_ASSOC);

		$pc = array();
		$result = $conn->query("select * from pc");
		while ($tuple = $result->fetchRow()) {
			if ($tuple['SPEED'] >= $speed && $tuple['PRICE'] < $budget) {
				//print_r($tuple);
				//echo "<br>";
				array_push($pc, $tuple);
			}
		}

		$laptop = array();
		$result = $conn->query("select * from laptop");
		while ($tuple = $result->fetchRow()) {
			if ($tuple['SPEED'] >= $speed && $tuple['PRICE'] < $budget) {
				//print_r($tuple);
				//echo "<br>";
				array_push($laptop, $tuple);
			}
		}

		$printer = array();
		$result = $conn->query("select * from printer");
		while($tuple = $result->fetchRow()) {
			if ($tuple['PRICE'] < $budget) {
				//print_r($tuple);
				//echo "<br>";
				array_push($printer, $tuple);
			}
		}
		$conn->setFetchMode(DB_FETCHMODE_ORDERED);


		// optimalsoln은 나중에 queryResult에 덮어씌울 예정.
		// queryResult라는 변수명이 헷갈릴 것으로 예상되어 새로 정의
		$optimalSoln = array(array("ty"=>'PC', "PRICE" => 2e8), array("ty"=>'Printer', "PRICE" => 2e8, "COLOR" => 0));

		$optimalPrice = 4e8;
		foreach($pc as $i => $x) {
			foreach($printer as $j => $y) {
				if ($optimalSoln[1]['COLOR'] <= $y['COLOR'] && $x['PRICE'] + $y['PRICE'] <= $budget && $optimalPrice > $x['PRICE'] + $y['PRICE']) {
					$optimalSoln[0] = $x;
					$optimalSoln[0]["ty"] = "PC";
					$optimalSoln[1] = $y;
					$optimalPrice = $x['PRICE'] + $y['PRICE'];
				}
			}
		}
		foreach($laptop as $i => $x) {
			foreach($printer as $j => $y) {
				if ($optimalSoln[1]['COLOR'] <= $y['COLOR'] && $x['PRICE'] + $y['PRICE'] <= $budget && $optimalPrice > $x['PRICE'] + $y['PRICE']) {
					$optimalSoln[0] = $x;
					$optimalSoln[0]["ty"] = "Laptop";
					$optimalSoln[1] = $y;
					$optimalPrice = $x['PRICE'] + $y['PRICE'];
				}
			}
		}

		$type = $optimalSoln[0]["ty"];
		$queryResult[$type] = $optimalSoln[0];
		$queryResult["Printer"] = $optimalSoln[1];
		unset($queryResult[$type]["ty"]);
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

			/* Implement an ouput screen */
			$result = find_system($conn, $_GET['budget'], $_GET['speed']);
			if ($result['Printer']['PRICE'] > 1e8) {
				echo "<p>주어진 제약을 만족하는 시스템이 없습니다.<br>예산이나 시스템 요구사양을 조정하여야 합니다.</p>";
			}
			else {
				echo "<p>예산 및 시스템 요구사양을 충족시키는 시스템을 찾았습니다.</p>";

				print_r($result);

				echo '<table border="1">';

				foreach($result as $type => $tuple) {
					echo "<b>".$type.":</b>";
					echo "<br>";
					foreach($tuple as $key=>$value) {
						echo $key.": ".(string)$value;
						echo "<br>";
					}
					echo "<br>";
				};

			}


			/* end of implement */
			$conn->disconnect();
		}
		include('../includes/footer.html');
	}
?>
