var main = {
    init : function (){
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },

    save : function () {
        var data = {
            name: $('#name').val(),
            password: $('#password').val(),
            city: $('#city').val(),
            street: $('#street').val(),
            zip: $('#zip').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('회원가입에 성공하셨습니다');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();

