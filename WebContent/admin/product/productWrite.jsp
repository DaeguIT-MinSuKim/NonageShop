<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
<h1>상품등록</h1>  
<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고,
인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
<form id="frm" name="frm" method="post" enctype="multipart/form-data">
<table id="list">
<tr>
  <th>상품분류</th>
  <td colspan="5">
  <select name="kind" id='kind'>
  	<option value='' disabled selected hidden>상품 종류를 선택하세요...</option>
    <c:forEach items="${kindList}" var="kind" >
      <option value="${kind.no}">${kind.name}</option>
   </c:forEach>
  </select>      
<tr>
  <th>상품명</th>
  <td width="343" colspan="5">
       <input type="text" id="name" name="name" size="47" maxlength="100" value="킬힐">
  </td>
</tr>
<tr>
  <th>원가[A]</th>
  <td width="70">
    <input type="text" id="price" name="price"  size="11" value="10000">
  </td>
  <th>판매가[B]</th>
  <td width="70">
     <input type="text" id="salePrice" name="salePrice" size="11" value="20000">
  </td>
  <th>[B-A]</th>
    <td width="72">
      <input type="text" id="margin" name="margin" size="11" readonly >
    </td>
  </tr>
    
  <tr>
    <th>상세설명</th>
    <td colspan="5">
      <textarea id="content" name="content" rows="8" cols="70" >이뻐요</textarea>
    </td>
  </tr>
  <tr>
    <th>상품이미지</th>
    <td width="343" colspan="5">
<!--  [2] 파일 업로드를 하기 위한 input 태그는 타입 속성 값을 file로 지정해야 한다.  -->
      <input type="file" id="image" name="image">
    </td>
  </tr>    
</table>
<input class="btn" type="button" value="등록" id="reg" >           
<input class="btn" type="button" value="취소" id="cancel">
</form> 
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>