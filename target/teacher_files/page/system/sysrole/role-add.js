layui.use([ 'form', 'layer'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/role_save.action" : "/teacher_files_war/role_update.action", {

            roleId: updateFlag === '0' ? null : $(".Id").val(),//id
            roleName: $(".roleName").val(),
            canLook : data.field.canLook,  //是否允许其他用户查看
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