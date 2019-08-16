layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    //渲染数据
    $.post("/teacher_files_war/biz/dept_findAll.action", function (data) {
        $("#parent").append('<option value="">是否包含从属于某个部门，若不属于可忽略</option>');
        $.each(data.data, function (index, item) {
            $('#parent').append(new Option(item.deptName, item.deptId));
        });
        //更新的时候使用中间变量选中 【上级部门】
        if (updateFlag==='1') {
            $('#parent').val($('.parentHide').val());
        }
        //重新渲染select
        form.render('select');
    });

    form.on("submit(addUser)", function (data) {
        // var membersId = [];
        // $('input[name="deptMembers"]:checked').each(function(){
        //     membersId.push($(this).val());//将选中的值添加到数组chk_value中
        // });

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/biz/dept_save.action" : "/teacher_files_war/biz/dept_update.action", {
            deptId: updateFlag === '0' ? null : $(".Id").val(),//id
            deptName: $(".deptName").val(),  //部门名称
            parentId: $("#parent").val(),  //上级部门
            deptPerson: $(".deptPerson").val(),  //负责人
            deptPhone: $(".deptPhone").val(),  //电话
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加部门！" : "修改部门");
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

    //添加验证规则
    var mobile = /^1[3|4|5|7|8]\d{9}$/,phone = /^0\d{2,3}-?\d{7,8}$/;
    form.verify({
        tellphone: function(value){
            var flag = mobile.test(value) || phone.test(value);
            if(!flag){
                return '请输入正确座机号码或手机号';
            }
        }
    });

});