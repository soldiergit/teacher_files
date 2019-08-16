layui.use(['form','layer','upload','laydate'],function(){
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;

    //管理员可以查看全部角色
    $.post("/teacher_files_war/role_findBySysAdmin.action", function (data) {
        $.each(data.data, function (index, item) {
            // $('#roleSet').append('<input type="checkbox" name="roleIds" value="'+item.roleId+'" lay-skin="primary" title="'+item.roleName+'"/>');
            $('#roleSet').append('<input type="radio" name="roleIds" value="'+item.roleId+'" title="'+item.roleName+'"/>');
        });
        var arr = $('.roleHide').val().split(',');
        if (arr != "" && arr.length != 0) {
            for ( var i = 0; i <arr.length; i++){
                $(".roleSet input[value="+arr[i]+"]").prop("checked","checked");
            }
        }
        //重新渲染radio
        form.render('radio');
    });

    form.on("submit(addUser)",function(data){
        var ids = [];
        var names = [];
        $('input[name="roleIds"]:checked').each(function(){
            ids.push($(this).val());//将选中的值添加到数组chk_value中
            names.push(this.title);//将选中的值添加到数组chk_value中
        });
        //弹出loading
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post("/teacher_files_war/biz/teacher_updateRole.action",{

            teacherId : $(".Id").val(),//id
            roleIds : ids.join(','),//角色
            roleNames : names.join(','),//角色名称
        },function(res){
            if (res.code === 0){
                top.layer.close(index);
                top.layer.msg("修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

});