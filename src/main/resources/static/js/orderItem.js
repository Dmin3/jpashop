var main = {
    init : function (){
        var _this = this;
        $('#orderItem').on('click', function () {
            _this.save();
        });
    },

    save : function () {
        var data = {
            id : $('#id').val(),
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
