<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<%--<c:import url="/common/navigation.jsp">--%>
<c:param name="title">
        成績登録システム
</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
<div class="my-2 text-end px-4"></div>
<form method="get">
<div class="row mb-3">
<div class="col-md-3">
<label class="form-label" for="student-f1-select1">入学年度</label>
<select class="form-select" id="student-f1-select1" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<label class="form-label" for="student-f1-select1">クラス</label>
<select class="form-select" id="student-f1-select1" name="f2">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<label class="form-label" for="student-f1-select1">科目</label>
<select class="form-select" id="student-f1-select1" name="f3">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>

                    <div class="col-md-3 d-flex align-items-end">
<input class="btn btn-secondary" type="submit" value="検索" name="end">
</div>
</div>
</form>

                        <form method="get">
<div class="row mb-3">
<div class="col-md-3">
<%--プルダウンを入力フォームに変更--%>
<p>
	<label class="form-label" for="student-f1-select1">学生番号</label>
	<input type="text" name="namae" id="name1">
</p>
<%--<select class="form-select" id="student-f1-select1" name="f1">--%>
<%-- <option value="0">--------</option>--%>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
<%--</select>--%>
</div>


<div align="right">
<input class="btn btn-secondary" type="submit" value="検索" name="end">
</div>
</div>
</form>

            <c:choose>
<c:when test="${students.size() > 0}">
<div>検索結果：${students.size()}件</div>
<table class="table table-hover">
<tr>
<th>入学年度</th>
<th>学生番号</th>
<th>氏名</th>
<th>クラス</th>
<%--<th class="text-center">在学中</th>--%>
<th></th>
<th></th>
</tr>
<c:forEach var="student" items="${students}">
<tr>
<td>${student.entYear}</td>
<td>${student.no}</td>
<td>${student.name}</td>
<td>${student.classNum}</td>
<td class="text-center">
<%-- 在学フラグが立っている場合「○」それ以外は「×」を表示 --%>
<c:choose>
<c:when test="${student.isAttend()}">
                                            ○
</c:when>
<c:otherwise>
                                            ×
</c:otherwise>
</c:choose>
</td>
<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<div>科目情報を検索または情報を入力してください。</div>
</c:otherwise>
</c:choose>
</section>
</c:param>
</c:import>
