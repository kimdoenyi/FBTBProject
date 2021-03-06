<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<script type="text/javascript">
	
	function numberFormat(inputNumber) {
	   return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	function getFormatDate(date){
	    var year = date.getFullYear().toString(); 
		year=year.substring(2,4);              //yyyy
	    var month = (1 + date.getMonth());          //M
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    var day = date.getDate();                   //d
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	    
	    var hours = date.getHours(); // 시
	    hours = hours >= 10 ? hours : '0' + hours;
	    var minutes = date.getMinutes();  // 분
	    minutes = minutes >= 10 ? minutes : '0' + minutes;
	    var seconds = date.getSeconds();  // 초
	    seconds = seconds >= 10 ? seconds : '0' + seconds;
	    return  year + '-' + month + '-' + day + '&nbsp&nbsp&nbsp&nbsp&nbsp' + hours + ':' + minutes + ':' + seconds;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
	}
	
	function sendData(){

		const urlParams = new URLSearchParams(window.location.search);
	
		var sort = document.getElementById("sort").value;
		var currPage = urlParams.get("currPage");
		if(currPage==null)
			currPage=1;
		
	      var xmlhttp = new XMLHttpRequest();
	      xmlhttp.onreadystatechange =  function(){
	                           
	         if(xmlhttp.readyState==4 && xmlhttp.status == 200){
	            
	            //JSON 형태로 받은 리스트
	            
	            var proList = JSON.parse(xmlhttp.responseText);
	  	
	          for(var i=0; i<$('#prolist tr').length-1; i++){	
	            		f_del_file();
	            }  
	          
	    
	          var innerHtml="";
	            for(pro of proList){
	            	innerHtml +="<tr>";
	            	innerHtml += "<td><input type=\"checkbox\" name=\"check\" value="+pro.storeorderdetailVo.store_order_detail_no+"></td>";
	                var store_order_detail_no= pro.storeorderdetailVo.store_order_detail_no;
	                innerHtml += "<td>"+store_order_detail_no+"</td>";
            		var proname= pro.productVo.product_name;
            		innerHtml += "<td>"+proname+"</td>";
            		var conqty= pro.processManagementVo.confirm_qty;
            		innerHtml += "<td>"+conqty+"</td>";
            		var protypename= pro.ProductTypeVo.product_type_name;
            		innerHtml += "<td>"+protypename+"</td>";
            		var proprice = numberFormat(pro.productVo.product_price); 
            		innerHtml += "<td>"+proprice+"</td>";
            		var prodate= new Date(pro.storeorderdetailVo.store_order_generating_time);
            		prodate= getFormatDate(prodate);
            		innerHtml += "<td>"+prodate+"</td>";
            		innerHtml+="</tr>";
	            }
				
	  
	            document.getElementById("tbody").innerHTML = innerHtml;
	            
	            for(var i=0; i<xxx.length; i++){
	          		var x= xxx[i].href.substring(0,57);
	         		
	          		xxx[i].href+=sort;

	          	}
	      
	         }                                 
	                           
	      };
	      
	      
	      var requestUrl = "${pageContext.request.contextPath }/sales/storeinpage_search_process.do?";

	      requestUrl += "sort=" + sort;
	      requestUrl += "&currPage=" + currPage;
	      
	      
	      
	      xmlhttp.open("get",requestUrl, true);                                  
	      xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	      xmlhttp.send();		
		
	
	
	      function f_del_file()
	      {
	         var lo_table = document.getElementById("prolist"); // 테이블지정
	         var li_rows  = lo_table.rows.length; // 테이블 row 개수(Tr의 개수)
	         var li_row_index = li_rows -1; // 테이블 row 즉 Tr의 고유 인덱스를 지정함

	         // tr이 하나도 없을때는 삭제하지 않는다.
	         if(li_row_index >= 0)
	         {
	            lo_table.deleteRow(li_row_index);
	         }  
	      }
	      

	}
	
</script>
</head>
<body>
<jsp:include page="../commons/global_nav.jsp"></jsp:include>
<!-- logistics navbar -->
    <c:if test="${sessionUser.deptTypeVo.dept_type_no==1}">
  <jsp:include page="../commons/management_nav.jsp"></jsp:include>
   </c:if>
     <c:if test="${sessionUser.deptTypeVo.dept_type_no==2}">
  <jsp:include page="../logistics/logistics_nav.jsp"></jsp:include>
   </c:if>
  <c:if test="${sessionUser.deptTypeVo.dept_type_no==3}">
  <jsp:include page="../commons/sales_nav.jsp"></jsp:include>
   </c:if>
     <c:if test="${sessionUser.deptTypeVo.dept_type_no==4}">
  <jsp:include page="../commons/production_nav.jsp"></jsp:include>
   </c:if>
<div class="container-fluid">
      <!-- 메인 기능들이 나오는 row -->
      <div class="row">
         <div class="col-1" style="background-color: #f9f9fa"></div>

         <!--  사이드바 시작  -->
         <div class="col-2"
            style="padding: 0; border-right: 2px solid #e8ecf1;">
            <ul class="list-group list-group-flush">
               <!--  수정할 부분 시작 -->
                <li class="list-group-item" style="height: 105px; background-color: #7393a7; color:white; font-weight:bold;"><h2 class="mt-3" align="center">재고관리</h2></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/product_page.do">재고조회</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/allproduct_page.do">제품조회</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/store_in_view_page.do">입고조회</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/store_in_page.do">입고등록</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/store_out_view_page.do">출고조회</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/sales/store_out_page.do">출고등록</a></h5></li>
               <!--  수정할 부분 끝 -->
            </ul>
         </div>
         <!--  사이드바 끝   -->

      
            <!--  메인 기능 나오는 곳 -->
            <div class="col pl-5 pr-5">
            <!--메인기능 내용시작-->
            <div class="row">
                <!--메인기능 메인col-->
                <div class="col" style="background-color:white">
                    <!--1. 타이틀, 네비게이터 시작-->
                    <div class="row mt-3">
                        <!--타이틀-->
                        <div class="col-4 mt-3" ><span style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; color: rgb(46, 46, 46); font-weight:bold; font-size: 36px;">입고등록</span></div>
                        <div class="col"></div>
                        <!---네비게이터-->
                        <div class="col-7">
                            <div class="row">                                   
                                <div class="col"></div>      
                            </div>
                            <div class="row"> 
                                <div class="col-2"></div> 
                                <div class="col mt-5" style="display:inline;">     
                                    <ul style="list-style:none; margin-top: 10px; margin-bottom: 5px; margin-left: 100px; text-decoration-color: gray">
                                        <li>
                                            <a style="color: gray; font-size: 14px" href="#">발주관리</a>
                                            >
                                            <a style="color: gray; font-size: 14px" href="#">입고등록</a>
                                      
                                        </li>
                                    </ul>             
                                </div>      
                            </div>
                        </div>
                    </div>
                    <!--1. 타이틀, 네비게이터 끝-->

                    <div class="mt-2 px-5 row" >
   
   <div class="col mx-5 px-5 py-3" style="background-color:#e8ecf1">
   <h2 class="text-center">미입고 리스트 조회</h2>
   
   
   

   
</div>
</div>



	<div class="row mt-5">
   	<div class="col"></div>
   	<div class="col-3">정렬
   		<select class="mt-1" name="sort" id="sort" onchange="sendData()">
           <option value=1 selected>발주상세번호순
			<option value=2>가격높은순
			<option value=3>가격낮은순
			<option value=4>확정수량높은순
			<option value=5>확정수량낮은순
         </select></div>
   	</div>
	<div class="row">
	<div class="col">
	<form action="./store_in_process.do" method="get">
	<div class="row"> <!-- 테이블 -->
				<div class="col">
					<table class="table table-hover shadow-sm p-3 mb-5 bg-white rounded" id="prolist">
						<thead class="shadow-none p-3 mb-5 bg-light rounded">
							<tr>
							<td>체크</td><td>발주상세번호</td><td>제품이름</td><td>확정수량</td>
							<td>제품유형</td><td>가격</td><td>발주날짜</td>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${store_order_list}" var="xxx">
								<tr>
									<td><input type="checkbox" name="check" value="${xxx.storeorderdetailVo.store_order_detail_no}"></td>
									<td>${xxx.storeorderdetailVo.store_order_detail_no}</td>
									<td>${xxx.productVo.product_name}</td>
									<td>${xxx.processManagementVo.confirm_qty}</td>	
									<td>${xxx.ProductTypeVo.product_type_name}</td>	
									<td><fmt:formatNumber value="${xxx.productVo.product_price}" pattern="#,###"/></td>
									<td><fmt:formatDate value="${xxx.storeorderdetailVo.store_order_generating_time}" pattern="yy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${xxx.storeorderdetailVo.store_order_generating_time}" pattern="HH:mm:ss"/></td>				
								</tr>
								</c:forEach>
						</tbody>
					
					</table>
				</div>
			</div>
			
			<div class="row mt-3"> <!-- 버튼들... -->
				<div class="col-8"> <!-- 페이지 버튼 -->
					<nav aria-label="Page navigation example">
					   <ul class="pagination">
					  
					    <li class="page-item<c:if test="${beginPage-1 <= 0}"> disabled</c:if>"><a class="ttt page-link" href="./store_in_page.do?currPage=${beginPage-1 }&sort=${param.sort}">이전</a></li>
					  	<c:forEach begin="${beginPage }" end="${endPage }" var="i">
					  		<li class="page-item<c:if test="${currPage==i}"> active</c:if>"><a class="ttt page-link" href="./store_in_page.do?currPage=${i}&sort=${param.sort}">${i}</a></li>
					  	</c:forEach>
					    <li class="page-item<c:if test="${endPage+1 >= (totalCount-1)/10+1 }"> disabled</c:if>"><a class="ttt page-link" href="./store_in_page.do?currPage=${endPage+1 }&sort=${param.sort}">다음</a></li>
					  </ul>

					</nav>
				</div>
				<div class="col">
		
				</div>
				<div class="col">
					<input type="submit" value="입고완료" class="btn btn-primary btn-block" >
				</div>
			</div>
			</form>
			</div>
		
			</div>
                        
                </div>        
            </div>
        <!--메인기능 내용끝-->

    </div>
         <!--  메인기능 나오는 곳 끝 -->

         <div class="col-1" style="background-color: #f9f9fa"></div>

      </div>

      <!--  footer  -->
      <jsp:include page="../commons/footer.jsp"></jsp:include>
    </div>

<jsp:include page="../commons/alert_unread_message.jsp"></jsp:include>
	 <jsp:include page="../sales/sales_alert.jsp"></jsp:include> 
	 <jsp:include page="../sales/sales_alert2.jsp"></jsp:include> 

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>