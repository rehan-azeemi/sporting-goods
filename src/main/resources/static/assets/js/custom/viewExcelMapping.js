$(document).ready(function() {
	var form_data = new FormData();                  
	form_data.append('action', 'getExcelColumn');
	form_data.append('excelFile', $('#excelFile').val());
$.ajax({
	       url: 'getFileData.php',
	       cache: false,
	       contentType: false,
	       processData: false,
	       data: form_data,                         
	       type: 'post',
	       success: function(php_script_response){
	        	for(var i in php_script_response){
	        		$('#source').append('<option value='+php_script_response[i].source+'>'+php_script_response[i].source+'</option>');
	        	}
	       }
	   });

var form_d = new FormData();
form_d.append('action','getDBColumn');
$.ajax({
	       url: 'getFileData.php',
	       cache: false,
	       contentType: false,
	       processData: false,
	       data: form_d,                         
	       type: 'post',
	       success: function(php_script_response){
	        	for(var i in php_script_response){
	        		$('#destination').append('<option value='+php_script_response[i].dbname+'>'+php_script_response[i].dbname+'</option>');
	        	}
	       }
	   });

$('#map-btn').on('click', function() {
		if($('#source').val() != '0' && $('#destination').val() != 0){
			$("#map-data").append('<tr><td>'+$('#source option:selected').text()+'</td><td>'+$('#destination option:selected').text()+'</td></tr>');
			$('#source option:selected').remove();
			$('#destination option:selected').remove();
		}
	});

$('#map-reset').on('click', function() {
		window.location = 'viewExcelMapping.php?excelFile='+$('#excelFile').val();
	});


$('#map-data-btn').on('click', function() {	
		var form = new FormData(); 
		var json_data = JSON.stringify(makeJsonFromTable('map-table'));
		form.append('mapped', json_data.substring(json_data.indexOf("["), json_data.length-1));
		form.append('excelFile',$('#excelFile').val());
		$.ajax({
	       url: 'excelMapping.php',
	       cache: false,
	       contentType: false,
	       processData: false,
	       data: form,                         
	       type: 'post',
	       success: function(php_script_response){
	        	if(php_script_response == 'success'){
					window.location = 'viewAllData.php';
				}
	       }
	   });
	});
})