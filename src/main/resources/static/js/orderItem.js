var main = {
    init : function (){
        var _this = this;
        $('#btn-orderItem').on('click', function () {
            _this.save();
        });
    },

    save : function () {
        var data = {
            id : $('#memberId').val(),
           count : $('#count').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/orderItem-save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('장바구니 추가가 완료되었습니다.');
            window.location.href = '/main';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();

// 모달창으로 데이터 받아오는 방법 data-target 사용
$('#exampleModal').on('show.bs.modal', function(e){
    var memberId = $(e.relatedTarget).data('test');
    document.getElementById('memberId').value = memberId;
});

