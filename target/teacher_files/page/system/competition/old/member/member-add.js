layui.use(['form','layer'],function(){
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form;

    //监听提交
    form.on("submit(addStudent)", function (data) {
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/biz/student_save.action" : "/teacher_files_war/biz/student_update.action", {
            itemId: $(".Id").val(),//项目id--必须有
            itemName: $(".itemName").val(),//项目名称
            studentId: updateFlag === '0' ? null : $(".studentId").val(),//学生id
            studentCode: $(".studentCode").val(),
            studentName: $(".studentName").val(),
            studentPhone: $(".studentPhone").val(),
            studentEmail: $(".studentEmail").val(),
            major: $(".major").val(),
            className: $(".className").val(),
            jobContent: $(".jobContent").val(),
            isPerson : data.field.isPerson
        }, function (res) {
            if (res.code === 0) {

                window.sessionStorage.setItem("thisRaceItemId", $(".Id").val());//暂时保存当前项目id，跳转之后马上删除！
                window.sessionStorage.setItem("thisRaceItemName", $(".itemName").val());//暂时保存当前项目名称，跳转之后马上删除！

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
    })
});