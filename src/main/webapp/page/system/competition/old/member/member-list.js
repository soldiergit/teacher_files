layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var thisRaceItemId = window.sessionStorage.getItem("thisRaceItemId");//获取id
    var thisRaceItemName = window.sessionStorage.getItem("thisRaceItemName");//获取名称
    //马上销毁
    window.sessionStorage.removeItem("thisRaceItemId");
    window.sessionStorage.removeItem("thisRaceItemName");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/student_findByRace.action?itemId='+thisRaceItemId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            {field: 'itemName', title: '项目名称', minWidth:100, align:"center"},
            {field: 'studentCode', title: '学生学号', minWidth:100, align:"center"},
            {field: 'studentName', title: '学生姓名', minWidth:200, align:'center'},
            {field: 'studentPhone', title: '手机号', minWidth:100, align:'center'},
            {field: 'studentEmail', title: '邮箱', minWidth:100, align:'center'},
            {field: 'major', title: '专业名称', minWidth:100, align:'center'},
            {field: 'className', title: '班级名称', minWidth:100, align:'center'},
            {field: 'jobContent', title: '项目分工', minWidth:100, align:'center'},
            {field: 'isPerson', title: '是否负责人', minWidth:100, align:'center', templet:function(d){
                    return d.isPerson=='1'?'是':'否';
                }},
            {title: '操作', minWidth:175, templet:'#infoListBar',fixed:"right",align:"center"}
        ]]
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher_files_war/biz/student_delete.action",{
                    itemId : data.itemId  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });


    $(".addBtn").click(function(){
        addUser();
    });
    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].studentId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/biz/student_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    });

    $(".returnBtn").click(function(){
        location.href = "../../my-guide-list.html";
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加项目成员",
            type : 2,
            content : "member-add.html",
            maxmin: true,
            area: ['800px', '600px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".Id").val(thisRaceItemId);
                body.find(".itemName").val(thisRaceItemName).prop("disabled",true);

                if(edit){
                    body.find(".studentId").val(edit.studentId);
                    body.find(".studentCode").val(edit.studentCode);
                    body.find(".studentName").val(edit.studentName);
                    body.find(".studentPhone").val(edit.studentPhone);
                    body.find(".studentEmail").val(edit.studentEmail);
                    body.find(".major").val(edit.major);
                    body.find(".className").val(edit.className);
                    body.find(".jobContent").val(edit.jobContent);
                    body.find(".contractor").val(edit.contractor);
                    body.find(".isPerson input[value="+edit.isPerson+"]").prop("checked","checked");  //队长
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addStudent");
                submit.click();
            }
        });
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});