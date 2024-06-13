<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Purchase List</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // �ı� �ۼ� ���� ���� �Լ�
        /*function openReviewForm(SHPostId) {
            $.get("/myPurchaseList/giveRate/" + SHPostId, function(data) {
                var reviewFormHtml = `
                    <div id="reviewForm">
                        <input type="hidden" id="SHPostId" value="${data.SHPostId}">
                        <input type="hidden" id="senderId" value="${data.senderId}">
                        <input type="hidden" id="receiverId" value="${data.receiverId}">
                        <label for="rating">Rating:</label>
                        <input type="number" id="rating" name="rating" min="1" max="5" required>
                        <label for="comment">Comment:</label>
                        <textarea id="comment" name="comment" required></textarea>
                        <button onclick="submitReview(${data.SHPostId})">Submit</button>
                        <button onclick="closeReviewForm()">Cancel</button>
                    </div>`;
                $("#reviewFormContainer").html(reviewFormHtml);
                $("#reviewFormContainer").show();
            });
        }*/
        function openReviewForm(SHPostId) {
            $.get("/myPurchaseList/giveRate/" + SHPostId, function(data) {
                $("#reviewFormContainer").html(data);
                $("#reviewFormContainer").show();
            });
        }

        // �ı� �ۼ� �� ���� �Լ�
        function submitReview(SHPostId) {
            var review = {
                SHPostId: SHPostId,
                senderId: $("#senderId").val(),
                receiverId: $("#receiverId").val(),
                rating: $("#rating").val(),
                comment: $("#comment").val()
            };

            $.ajax({
                url: "/myPurchaseList/giveRate/" + SHPostId,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(review),
                success: function(response) {
                    alert(response);
                    $("#reviewFormContainer").hide();
                    // ��ư �ؽ�Ʈ�� '�ı� �ۼ� �Ϸ�'�� ����
                    $("#reviewButton_" + SHPostId).text("�ı� �ۼ� �Ϸ�").prop("disabled", true);
                },
                error: function(xhr, status, error) {
                    alert("�ı� �ۼ��� �����߽��ϴ�. �ٽ� �õ����ּ���.");
                }
            });
        }
        
     	// �ı� �ۼ� ���� �ݴ� �Լ�
        function closeReviewForm() {
            $("#reviewFormContainer").hide();
        }
    </script>
</head>
<body>
    <!-- ���� �ڵ� ... -->

     <div id="reviewFormContainer" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background:white; padding:20px; box-shadow:0px 0px 10px rgba(0,0,0,0.5);">
        <!-- ���� ���� �������� ���Ե˴ϴ� -->
    </div>

    <table>
        <thead>
            <!-- Table Headers -->
        </thead>
        <tbody>
            <c:forEach var="item" items="${SHPurchaseList}">
                <tr>
                    <!-- ���� ������ ���� -->
                    <td>
                        <button id="reviewButton_${item.SHPostId}" onclick="openReviewForm(${item.SHPostId})">
                            �ı� �ۼ��ϱ�
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- �ı� �ۼ� �� ���ø� -->
    <script type="text/template" id="reviewFormTemplate">
        <div>
            <input type="hidden" id="SHPostId" value="<%= SHPostId %>">
            <input type="hidden" id="senderId" value="<%= senderId %>">
            <input type="hidden" id="receiverId" value="<%= receiverId %>">
            <label for="rating">Rating:</label>
            <input type="number" id="rating" name="rating" min="1" max="5" required>
            <label for="comment">Comment:</label>
            <textarea id="comment" name="comment" required></textarea>
            <button onclick="submitReview(<%= SHPostId %>)">Submit</button>
        </div>
    </script>
</body>
</html>
