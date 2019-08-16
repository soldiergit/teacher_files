layui.use([ 'form', 'layer'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form;

    $.post("/teacher_files_war/menu_findAll.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#menuSet').append('<input type="checkbox" name="menuIds" value="'+item.menuId+'" lay-skin="primary" title="'+item.title+'"/><br/>');
        });

        //多选框默认选中--js传递过来的数组会变成字符串，所有要转换--记得数组非空判断
        var arr = $('.menuHide').val().split(',');
        if (arr != "" && arr.length != 0) {
            for ( var i = 0; i <arr.length; i++){
                $(".menuSet input[value="+arr[i]+"]").prop("checked","checked");
            }
        }
        //重新渲染checkbox
        form.render('checkbox');
    });

    form.on("submit(addUser)", function (data) {
        var ids = [];
        // var names = [];
        $('input[name="menuIds"]:checked').each(function(){
            ids.push($(this).val());//将选中的值添加到数组chk_value中
            // names.push(this.title);//将选中的值添加到数组chk_value中
        });
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/teacher_files_war/role_updateMenu.action", {

            roleId: $(".Id").val(),//id
            roleName: $(".roleName").val(),
            canLook : data.field.canLook,  //是否允许其他用户查看

            ids: ids.join(',')

        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg("修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });
});