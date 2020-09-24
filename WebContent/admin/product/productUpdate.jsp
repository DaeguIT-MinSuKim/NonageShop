<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
<h1>상품수정</h1>  
<form id="frm" name="frm" method="post" enctype="multipart/form-data">
<input type="hidden" name="pno" value="${product.no}">
<input type="hidden" name="code" >
<input type="hidden" name="nonmakeImg" value="${product.image}">
<table id="list">
  <tr>
    <th>상품분류</th>
    <td colspan="5">
    <select name="kind" id='kind'>
      <c:forEach items="${kindList}" var="kind" >
        <c:choose>
          <c:when test="${product.kind+1 == kind.no}">
            <option value="${kind.no}" selected="selected">${kind.name}</option>
          </c:when>
          <c:otherwise>
            <option value="${kind.no}">${kind.name}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select> 
    </td>
  </tr>
  <tr>
    <th>상품명</th>
    <td width="343" colspan="5">
      <input type="text" id="name" name="name" size="47" maxlength="100" value="${product.name}">
    </td>
  </tr>
  <tr>
    <th>원가[A]</th>
    <td width="70">        
      <input type="text" id="price" name="price" size="11"  value="${product.price}">
    </td>
    <th>판매가[B]</th>
    <td width="70">
      <input type="text" id="salePrice" name="salePrice" size="11" value="${product.salePrice}">
    </td>
    <th>[B-A]</th>
    <td width="72">
      <input type="text" id="margin" name="margin" size="11" readonly >
    </td>
  </tr>
  <tr>
    <th>베스트상품</th>
    <td>
      <c:choose>
        <c:when test='${product.bestYn=="y"}'>
          <input type="checkbox" id="bestYn" name="bestYn" value="y" checked="checked">
        </c:when>
        <c:otherwise>
          <input type="checkbox" id="bestYn" name="bestYn" value="n">
        </c:otherwise>
      </c:choose>
    </td>        
    <th>사용유무</th>
    <td>
      <c:choose>
        <c:when test='${product.delYn=="y"}'>
          <input type="checkbox" id="delYn" name="delYn" value="y" checked="checked">
        </c:when>
      <c:otherwise>
        <input type="checkbox" id="delYn" name="delYn" value="n">
      </c:otherwise>
    </c:choose>
    </td>
  </tr>
  <tr>
    <th>상세설명</th>
    <td colspan="5">
      <textarea id="content" name="content" rows="8" cols="70" >${product.content}</textarea>
    </td>
  </tr>
  <tr>
    <th>상품이미지</th>
    <td colspan="5">
      <img src="product_images/${product.image}" width="200pt">     
      <br>
      <input type="file" id="image" name="image">
    </td> 
  </tr>    
</table>
<input class="btn" type="button" id="modify" value="수정">           
<input class="btn" type="button" id="cancel" value="취소">
</form> 
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>