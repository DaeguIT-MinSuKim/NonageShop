$(function(){
    $('.detail').on("click",function(){
        var info = $(this).data('info');
        alert(info[0] + " > " + info[1]);
        
        $("#frm").attr("action", "adminProductDetail.do?tpage=" + info[0] +"&pno="+info[1]);
        $('#frm').submit();
    });
    
    if ($('#price').length){
        var a = $('#price').val().replace(/,/g, '');
        var b = $('#salePrice').val().replace(/,/g, '');
        var ab = parseInt(b) - parseInt(a);
        $('#margin').val(ab);
    }
    
	$('#btn_write').on("click", function(){
	    $("#frm").attr("action", "adminProductWriteForm.do");
	    $('#frm').submit();
	});
	
	$('#price, #salePrice, #margin').on("keyup", function(){
	    var t = $(this).val().replace(/,/g, '');
		var result = Number(t).toLocaleString('ko');
		$(this).val(result);
	});
	
	$('#salePrice').on("blur",function(){
		var a = $('#price').val().replace(/,/g, '');
		var b = $('#salePrice').val().replace(/,/g, '');
		var ab = parseInt(b) - parseInt(a);
		$('#margin').val(ab).toLocaleString('ko');
	});

	$('#cancel').on("click",function(){
		$("#frm").attr("action", "adminProductList.do");
		$('#frm').submit();
	});
	
	$('#modifyPdt').on("click",function(){
        var info = $(this).data('info');
        $("#frm").attr("action", "adminProductUpdateForm.do?tpage=" + info[0] + "&pno=" + info[1]);
        $('#frm').submit();
    });

	$('#prevList').on("click",function(){
	    var info = $(this).data('info');
	    $("#frm").attr("action", "adminProductList.do?tpage=" + info);
	    $('#frm').submit();
	});
	
    $('#btn_search').on("click",function(){
        var key = $('input[name="key"]').val();
        alert("key > " + key);
        $("#frm").attr("action", "adminProductList.do");
        $('#frm').submit();
    });
	   
    $('#btn_total').on("click",function(){
        $('input[name="key"]').val("");
        $("#frm").attr("action", "adminProductList.do");
        $('#frm').submit();
    });   
    
    $('#reg').on("click",fn_save);
    
    $('#modify').on("click",fn_modify);
});

fn_save=function go_save() {
    if ($('#kind option:selected').val().length == 0) {
        alert('상품분류를 선택하세요.');
        $('#kind').focus();
    } else if ($('#name').val().length == 0) {
        alert('상품명을 입력하세요.');
        $('#name').focus();
    } else if ($('#price').val().length == 0) {
        alert('원가를 입력하세요.');
        $('#price').focus();
    } else if ($('#salePrice').val().length == 0) {
        alert('판매가를 입력하세요.');
        $('#salePrice').focus();
    } else if ($('#content').val().length == 0) {
        alert('상품상세를 입력하세요.');
        $('#content').focus();
    } else if ($('#image').val().length == 0) {
        alert('상품이미지들 입력하세요.');
        $('#image').focus();
    } else {
        $('#price').val($('#price').val().replace(/,/gi, ""));
        $('#salePrice').val($('#salePrice').val().replace(/,/gi, ""));
        $('#margin').val($('#margin').val().replace(/,/gi, ""));

        $("#frm").attr( "enctype", "multipart/form-data" );
        $("#frm").attr("action", "adminProductWrite.do");
        $('#frm').submit();
    }
}

fn_modify=function go_mod_save() {
    if ($('#kind option:selected').val().length == 0) {
        alert('상품분류를 선택하세요.');
        $('#kind').focus();
    } else if ($('#name').val().length == 0) {
        alert('상품명을 입력하세요.');
        $('#name').focus();
    } else if ($('#price').val().length == 0) {
        alert('원가를 입력하세요.');
        $('#price').focus();
    } else if ($('#salePrice').val().length == 0) {
        alert('판매가를 입력하세요.');
        $('#salePrice').focus();
    } else if ($('#content').val().length == 0) {
        alert('상품상세를 입력하세요.');
        $('#content').focus();
    } else {
        if (confirm('수정하시겠습니까?')) {
            // [1] 상품을 삭제하지 않는 대신 사용하지 않음을 products 테이블의 useyn 컬럼에 1을 채워 넣기 위해서
            // useyns hidden 태그에 1을 지정한다.
            if ($("#delYn").is(':checked')) {
                $("#delYn").val("y");
            }
            if($("#bestYn").is(':checked')) {
                $("#bestYn").val("y");
            }

            $("#frm").attr("enctype", "multipart/form-data" );
            $("#frm").attr("action", "adminProductUpdate.do");
            $('#frm').submit();
        }
    }
}
