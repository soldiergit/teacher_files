layui.use([ 'form', 'layer'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    //渲染下拉框
    $.post("/teacher_files_war/menu_findAll.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#parentBunk').append(new Option(item.title, item.menuId));
        });
        $('#parentBunk').val($('.parentHide').val());//使用中间变量让其默认选中

        //重新渲染select
        form.render('select');
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/menuChildren_save.action" : "/teacher_files_war/menuChildren_update.action", {

            id: updateFlag === '0' ? null : $(".Id").val(),//id
            parentId: $(".parentId").val(),//父级菜单
            title: $(".title").val(),
            icon: $(".icon").val(),
            href: $(".href").val(),
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加成功！" : "修改成功！");
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