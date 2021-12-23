<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<script>
function frm_submit(){
      var frm = document.getElementById("frm"); 
   
      if(document.getElementById("selectBox").selectedIndex==0){ 
         alert("물류팀을 선택해 주세요.");
         return; //값을 DB로 전달하지 않고 return
      }
      //셀렉트 박스 예외 처리
      if(document.getElementById("selectBox2").selectedIndex==0){ 
         alert("지점을 선택해 주세요.");
         return; //값을 DB로 전달하지 않고 return
      }
      //셀렉트 박스 예외 처리
      
      frm.submit(); //값을 DB로 전달
}
</script>
<meta charset="UTF-8">
<title>page</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
   integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
   crossorigin="anonymous">

</head>

<body>
   <jsp:include page="../commons/global_nav.jsp"></jsp:include>
   <jsp:include page="../commons/management_nav.jsp"></jsp:include>

   <div class="container-fluid">
      <!-- 메인 기능들이 나오는 row -->
      <div class="row">
         <div class="col-1" style="background-color: #f9f9fa"></div>

         <!--  사이드바 시작  -->
         <div class="col-2"
            style="padding: 0; border-right: 2px solid #e8ecf1;">
            <ul class="list-group list-group-flush">
               <!--  수정할 부분 시작 -->
               <li class="list-group-item" style="height: 105px; background-color: #7393a7; color:white; font-weight:bold;"><h2 class="mt-3" align="center">부서관리</h2></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/management/store_view.do">매장관리</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/management/factory_view.do">공장관리</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/management/logistics_management.do">물류관리</a></h5></li>
               <li class="list-group-item"><h5><a style="color: #6c737e;" href="${pageContext.request.contextPath }/management/logistics_add.do">물류지점등록</a></h5></li>
               
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
                    <div class="col-4 mt-3" ><span style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; color: rgb(46, 46, 46); font-weight:bold; font-size: 36px;">물류 지점 등록</span></div>
                    <div class="col"></div>
                    <!---네비게이터-->
                    <div class="col-6">
                        <div class="row">                                   
                            <div class="col"></div>      
                        </div>
                        <div class="row"> 
                            <div class="col-2"></div> 
                            <div class="col mt-5" style="display:inline;">     
                                <ul style="list-style:none; margin-top: 10px; margin-bottom: 5px; margin-left: 100px; text-decoration-color: gray">
                                    <li>
                                        <a style="color: gray; font-size: 14px" href="#">부서관리</a>
                                        >
                                        <a style="color: gray; font-size: 14px" href="${pageContext.request.contextPath }/management/logistics_add.do">물류지점등록</a>
                                       
                                        
                                    </li>
                                </ul>             
                            </div>      
                        </div>
                    </div>
                </div>
                <!--1. 타이틀, 네비게이터 끝-->

                <!--2. 메인기능 알맹이들!!!!! 여기에 넣기-->
                <!--2. 메인기능 알맹이들!!!!! 여기에 넣기-->
                <!--2. 메인기능 알맹이들!!!!! 여기에 넣기-->
                <!--2. 메인기능 알맹이들!!!!! 여기에 넣기-->
   <c:if test="${!empty sessionUser && sessionUser.deptTypeVo.dept_type_no==1}">
            <form action="./logistics_add_process.do" method="post" id="frm">

            <!-- 테이블 내용 -->
               <div class="row mt-5">
                  <div class="col-3"></div>
                  <div class="col">
                  <table class="table table-hover shadow-sm p-3 mb-2 bg-white rounded">
                     <tbody>
                     <tr>
                        <th class="shadow-none p-3 mb-5 bg-light rounded">물류팀</th>
                        <td>
                       <select id="selectBox" name="dept_no"  class="form-control">
                   <option value="">물류팀 선택</option>
                   
                     <option value="21">물류1팀</option>   
                  <option value="22">물류2팀</option>
                  <option value="23">물류3팀</option>
                  </select><input type="hidden" name="dept_type_no" value="2">
                   
                        </td>
                        <td></td>
                     </tr>
                     
                     <tr>
                        <th class="shadow-none p-3 mb-5 bg-light rounded">지점명</th>
                        <td>
                    <select id="selectBox2" name="branch_name"  class="form-control">
                   <option value="">매장 선택</option>
                   <c:forEach var="cccc" items="${storeList }">
                     <option value="${cccc.branchVo.branch_name }">${cccc.branchVo.branch_name }</option>   
                  </c:forEach>
               </select>
                      </td>
                        <td></td>
                     </tr>
                     
                     
                     </tbody>
                  </table>
                  
                     <!-- 버튼들 -->
                     <div class="row mt-3 mb-5">
                        <div class="col"></div>
                        <div class="col"></div>
                        <div class="col-3">
                         <input type="button" value="등록" class="btn btn-primary btn-block" onclick="frm_submit()">
                        </div>
                     </div>
                     
                  </div>
                  <div class="col-3"></div>
               </div>
            </form></c:if><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



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
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"></script>
   <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
      integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
      crossorigin="anonymous"></script>
   <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
      integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
      crossorigin="anonymous"></script>
</body>
</html>