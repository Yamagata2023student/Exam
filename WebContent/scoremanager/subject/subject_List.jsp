<%-- 科目管理JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
        科目管理システム
	</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
				<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
				</div>
			<form method="get">
			<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</div>
			</form>
			<c:choose>
			<c:when test="${not empty subjects}">
			<table class="table table-hover">
			<tr>
			<th>科目コード</th>
			<th>科目名</th>
			<th></th>
			<th></th>
			</tr>
			<c:forEach var="subject" items="${subjects}">
			<tr>
			<td>${subject.cd}</td>
			<td>${subject.name}</td>
			<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
			<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
			</tr>
			</c:forEach>
			</table>
			</c:when>
			<c:otherwise>
				<p>科目が見つかりませんでした。</p>
			</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>