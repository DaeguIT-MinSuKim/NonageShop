//projectList.jsp
function go_search() {
	var theForm = document.frm;
	theForm.action =  "adminProductList.do";
	theForm.submit();
}

function go_total() {
	var theForm = document.frm;
	theForm.key.value = "";
	theForm.action =  "adminProductList.do";
	theForm.submit();
}

function go_detail(tpage, pno) {
	$("#frm").attr("action", "adminProductDetail.do?tpage=" + tpage+"&pno="+pno);
	$('#frm').submit();
}

$(function(){
	$('#btn_write').on("click", fn_go_wrt);
	
	$('#price, #salePrice, #margin').on("keyup", function(){
		var result = Number($(this).val()).toLocaleString('ko');
		$(this).val(result);
	});
	
	$('#salePrice').on("blur",function(){
		var a = $('#price').val().replace(/,/g, '');
		var b = $('#salePrice').val().replace(/,/g, '');
		var ab = parseInt(b) - parseInt(a);
		$('#margin').val(ab);
	});
	

	$('#cancel').on("click",function(){
		$("#frm").attr("action", "adminProductList.do");
		$('#frm').submit();
	});
	
	$('#reg').on("click",fn_save);
});

fn_go_wrt=function go_wrt() {
	$("#frm").attr("action", "adminProductWriteForm.do");
	$('#frm').submit();
}

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

// **************** productDetail.jsp
function go_list(tpage) {
	var theForm = document.frm;
	//상품 리스트로 이동하되 현재 페이지를 쿼리 스트링으로 넘긴다.
	theForm.action = "adminProductList.do?tpage=" + tpage;
	theForm.submit();
}
// **************** productDetail.jsp
function go_mod(tpage, pseq) {
	var theForm = document.frm;
	//현재 페이지를 쿼리 스트링으로 넘긴다.
	theForm.action = "NonageServlet?command=admin_product_update_form&tpage=" + 
		              tpage+"&pseq="+pseq;
	theForm.submit();
}

function go_mod_save(tpage, pseq) {
	var theForm = document.frm;

	if (theForm.kind.value == '') {
		alert('상품분류를 선택하세요');
		theForm.kind.focus();
	} else if (theForm.name.value == '') {
		alert('상품명을 입력하세요');
		theForm.name.focus();
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요');
		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요');
		theForm.price2.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요');
		theForm.content.focus();
	} else {
		if (confirm('수정하시겠습니까?')) {
			// [1] 상품을 삭제하지 않는 대신 사용하지 않음을 products 테이블의 useyn 컬럼에 1을 채워 넣기 위해서
			// useyns hidden 태그에 1을 지정한다.
			if (theForm.useyn.checked == true) {
				theForm.useyn.value = "y";
			}
			if(theForm.bestyn.checked == true) {
				theForm.bestyn.value = "y";
			}
			theForm.encoding = "multipart/form-data";
			// theForm.seq.value=seq;
			theForm.price1.value = removeComma(theForm.price1);
			theForm.price2.value = removeComma(theForm.price2);
			theForm.price3.value = removeComma(theForm.price3);
			// [2] products 테이블의 상품 정보를 수정하는 처리를 하는 product_modsave.jsp 페이지로
			// 이동하되 상품 코드를 전달해준다. 상품코드로 폴더를 생성해서 그곳에 이미지 파일을 업로드하기 때문이다.			
			theForm.action = "NonageServlet?command=admin_product_update";
			theForm.submit();
		}
	}
}

function go_mod_mov(tpage, pseq) {
	var theForm = document.frm;
	theForm.action = 'NonageServlet?command=admin_product_detail&tpage=' + tpage + "&pseq=" + pseq;
	theForm.submit();
}
