select 1 from member where id='one';
select ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE 
  from member where id='one';
 
SELECT * FROM MEMBER;
SELECT count(*) FROM address;

select ZIP_NUM, SIDO, GUGUN, DONG, ZIP_CODE, BUNJI from address where dong like '%구암동%';

-- cart
insert into cart(memberid, pno, quantity) values('one', 1, 1);
select * from cart_view where id='one' order by cseq DESC;

SELECT c."NO" , c.MEMBERID , c.PNO , m.NAME , p.NAME, c.QUANTITY, c.REG_DATE, p.MARGIN , c.RESULT 
  FROM cart c JOIN MEMBER m ON c.MEMBERID = m.ID JOIN PRODUCT p ON c.PNO =p."NO" 
 WHERE result = '1';
 
-- ORDER
SELECT *
  FROM USER_SEQUENCES;

 SELECT ORDER_DETAIL_NO_SEQ.CURRVAL FROM dual;

select max(no) from orders;
SELECT * FROM ORDER_DETAIL;

-- 
SELECT * FROM cart;

--

SELECT * FROM ORDERs;
SELECT * FROM ORDER_DETAIL;
SELECT * FROM order_view;

SELECT DNO, ONO, MID, ORDER_DATE, PNO, QUANTITY, MNAME, ZIP_NUM, ADDRESS, PHONE, PNAME, SALEPRICE, "RESULT"
  FROM ORDER_VIEW WHERE MID='ONE' AND RESULT LIKE '%1%' AND ONO=1;