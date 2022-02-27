var main = {
    init : function (){
        var _this = this;
        $('#btn-itemSave').on('click', function () {
            _this.save();
        });
    },

    save : function () {
        var data = {
            name: $('#name').val(),
            price: $('#price').val(),
            stockQuantity: $('#stockQuantity').val(),
            dtype: $('#dtype').val(),
            company: $('#company').val(),
            etc: $('#etc').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/itemSave',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('상품 등록이 완료되었습니다.');
            window.location.href = '/home';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();

