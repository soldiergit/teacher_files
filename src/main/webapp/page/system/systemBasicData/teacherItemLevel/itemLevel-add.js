layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/biz/teacherItemLevel_save.action" : "/teacher_files_war/biz/teacherItemLevel_update.action", {
            id: updateFlag === '0' ? null : $(".Id").val(),//id
            title: $(".title").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加教师项目级别！" : "修改教师项目级别！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                top.layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

});