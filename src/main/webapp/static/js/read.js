var storage = window.localStorage;
var isHideAudio = storage.getItem("isHideAudio");

// https://file.oss.bbcow.com/
// http://oikrsbhcw.bkt.clouddn.com/
// 初始化阅读页
function loadContent(resource_url, txt_id, audio_token, client_ip){
	// 全站控制语音
	if(isHideAudio == "true"){
		$("#hide_audio").attr("checked","checked");
	}
	
	$.ajax({
		url : 'https://file.oss.bbcow.com/'+resource_url,
		success : function(data) {
			// 文本转换html
			toHtml(data, audio_token, client_ip);
			
			// 内容元素绑定事件
			bindClick(txt_id);
			
			// 加载本地标签，定位
			locationBookmark(txt_id);
		}
	});
}

function toHtml(data, audio_token, client_ip){
	var arr = data.split("\n");
	var len = arr.length;
	var num = 1;
	for(var i=0; i < len;i++){
		if(arr[i] != ""){
			$("#content").append("<p class='lead content' id='c"+num+"'><var><font color='red'>"+num+"   </font></var>"+arr[i]+"</p>");
			
			$("#content").append(audio(num, audio_token, client_ip));
			
			progressBar(i/len*100);
			num++;
		}
	}
	progressBar(100);
}

function locationBookmark(txt_id){
	if (storage.getItem(""+txt_id+".bookmark")){
		var p_id = storage.getItem(""+txt_id+".bookmark");
		location.href = "#"+p_id;
		
		$("#"+p_id+"").append('<span class="glyphicon glyphicon-bookmark" style="color:blue"></span>');
	}
}

function hideAudio(obj){
	if ($(obj).attr('checked') == undefined) {
		$(".audio").hide();
		$(obj).attr("checked","checked");
		
		storage.setItem("isHideAudio", true);
	} else {
		$(".audio").show();
		$(obj).removeAttr("checked");
		storage.setItem("isHideAudio", false);
	}
	
}

function bindClick(txt_id){
	$(".content").bind("click",function(){
		var p_id = $(this).attr("id");
		storage.setItem(""+txt_id+".bookmark", p_id);
		$("#"+p_id+"").append('<span class="glyphicon glyphicon-bookmark" style="color:blue"></span>');
	});
}

function audio(num, audio_token, client_ip) {
	var text = $("#c" + num + "").text();
	if(text != null){
		text = text.replace((num), "")
	}
	
	var x = document.createElement("AUDIO");
	x.setAttribute("preload", "none");
	x.setAttribute("src", "http://tsn.baidu.com/text2audio?tex="+text+"&lan=zh&tok="+audio_token+"&ctp=1&cuid="+client_ip);
	x.setAttribute("controls", "controls");
	x.setAttribute("class", "audio");
	
	if (isHideAudio == "true"){
		$(x).css("display","none");
	}
	
	return x;
}

// 进度条
function progressBar(percent){
	$("#progress_bar").css("width" ,percent+"%");
	$("#progress_bar").attr("aria-valuenow", percent);
	$("#progress_bar").text(percent+"%");
}

// 评论
function publishComment(txt_id, url){
	var remark = $("#remark").val();
	var invitation_code = $("#invitation_code").val();
	
	// 本地重复评论过滤，并突出显示
	if (storage.getItem(""+txt_id+".comment")){
		$('#submit').attr("disabled","disabled");
		
		$("."+invitation_code+"").css("background","red");
		
		$('#submit').text("已评论");
		return;
	}
	
	if(checkNull(remark)){
		$('#submit').attr("data-content","评论内容必填");
		$('#submit').popover("show");
		return;
	}
	if(checkNull(invitation_code)){
		$('#submit').attr("data-content","邀请码必填");
		$('#submit').popover("show");
		return;
	}
	
	$.ajax({
		method : "POST",
		data : {id : txt_id, remark : remark, invitation_code : invitation_code},
		url : url,
		success : function(data) {
			
			storage.setItem(""+txt_id+".comment", invitation_code);
			
			$('#submit').attr("disabled","disabled");
			$('#submit').text("评论成功");
			// 清除内容
			$("#remark").val("");
			$("#invitation_code").val("");
			
			// 联动显示发布的评论
			var panel = document.createElement("div");
			panel.setAttribute("class", "panel panel-default");
			
			var panel_head = document.createElement("div");
			panel_head.setAttribute("class", "panel-heading");
			panel_head.innerHTML = invitation_code;
			
			var panel_body = document.createElement("div");
			panel_body.setAttribute("class", "panel-body");
			panel_body.innerHTML = remark;

			$(panel).append(panel_head);
			$(panel).append(panel_body);
			$("#o_comments").append(panel);
		}
	});
}

function checkNull(data){
	data = data.replace(/(^\s*)|(\s*$)/g, "");
	return (data == "" || data == undefined || data == null) ? true : false; 
}