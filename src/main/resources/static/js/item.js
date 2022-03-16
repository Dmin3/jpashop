var main = {
    init : function (){
        var _this = this;
        $('#btn-itemSave').on('click', function () {
            _this.save();
        });
        $('#btn-itemUpdate').on('click', function (){
            _this.update();
        });
        $('#btn-itemRemove').on('click', function (){
            _this.remove();
        })

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
            url: '/api/v1/item-save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('상품 등록이 완료되었습니다.');
            window.location.href = '/main';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        var data = {
            name: $('#name').val(),
            price: $('#price').val(),
            stockQuantity: $('#stockQuantity').val(),
            company: $('#company').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/item-update/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (){
            alert('상품 수정완료')
            window.location.href = '/main';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    remove : function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/item-remove/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (){
            alert('상품 삭제 완료')
            window.location.href = '/main';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();

