/**
 * 서버가 실행될 때 초기화 시켜주는 Servlet
 * 작성자 : 궁금해조
 * 작성일 : 2021.03.21
 * ver_1.0
 */ 
package com.mbti.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.mbti.board.controller.BoardController;
import com.mbti.board.dao.BoardReplyDAO;
import com.mbti.board.dao.BoardDAO;
import com.mbti.board.service.BoardDeleteService;
import com.mbti.board.service.BoardListService;
import com.mbti.board.service.BoardReplyDeleteService;
import com.mbti.board.service.BoardReplyListService;
import com.mbti.board.service.BoardReplyUpdateService;
import com.mbti.board.service.BoardReplyWriteService;
import com.mbti.board.service.BoardUpdateService;
import com.mbti.board.service.BoardViewService;
import com.mbti.board.service.BoardWriteService;
import com.mbti.error.controller.ErrorController;
import com.mbti.feedback.controller.feedbackController;
import com.mbti.feedback.dao.FeedbackDAO;
import com.mbti.feedback.service.FeedbackAdminListService;
import com.mbti.feedback.service.FeedbackAnswerService;
import com.mbti.feedback.service.FeedbackDeleteService;
import com.mbti.feedback.service.FeedbackListService;
import com.mbti.feedback.service.FeedbackViewService;
import com.mbti.feedback.service.FeedbackWriteService;
import com.mbti.hit.service.MBTIHitService;
import com.mbti.list.controller.ListController;
import com.mbti.list.dao.ListDAO;
import com.mbti.list.service.ListDeleteService;
import com.mbti.list.service.ListListService;
import com.mbti.list.service.ListRegisterService;
import com.mbti.list.service.ListUpdateService;
import com.mbti.list.service.ListViewService;
import com.mbti.member.controller.MemberController;
import com.mbti.member.dao.MemberDAO;
import com.mbti.member.service.MemberCheckIdService;
import com.mbti.member.service.MemberDeleteService;
import com.mbti.member.service.MemberGradeModifyService;
import com.mbti.member.service.MemberListService;
import com.mbti.member.service.MemberLoginService;
import com.mbti.member.service.MemberUpdateService;
import com.mbti.member.service.MemberViewService;
import com.mbti.member.service.MemberWriteService;
//import com.mbti.member.dao.MemberDAO;
//import com.mbti.member.service.MemberCheckIdService;
//import com.mbti.member.service.MemberGradeModifyService;
//import com.mbti.member.service.MemberListService;
//import com.mbti.member.service.MemberLoginService;
//import com.mbti.member.service.MemberViewService;
//import com.mbti.member.service.MemberWriteService;
//import com.mbti.memeber.controller.MemberController;
import com.mbti.notice.controller.NoticeController;
import com.mbti.notice.dao.NoticeDAO;
import com.mbti.notice.dao.NoticeReplyDAO;
import com.mbti.notice.service.NoticeDeleteService;
import com.mbti.notice.service.NoticeListService;
import com.mbti.notice.service.NoticeReplyDeleteService;
import com.mbti.notice.service.NoticeReplyListService;
import com.mbti.notice.service.NoticeReplyUpdateService;
import com.mbti.notice.service.NoticeReplyWriteService;
import com.mbti.notice.service.NoticeViewService;
import com.mbti.notice.service.NoticeWriteService;
import com.mbti.result.controller.ResultController;
import com.mbti.type.controller.TypeController;
import com.mbti.type.dao.TypeDAO;
import com.mbti.type.service.TypeDeleteService;
import com.mbti.type.service.TypeListService;
import com.mbti.type.service.TypeResultService;
import com.mbti.type.service.TypeUpdateFileService;
import com.mbti.type.service.TypeViewService;
import com.mbti.type.service.TypeWriteService;

/**
 * Servlet implementation class Init
 */
@WebServlet(value = "/Init", loadOnStartup = 1)
public class Init extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("MBTI 서버 실행 ======================================================================= ");
		
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// Main ==========================================================================
		// controller 생성 -> 저장
		System.out.println("test");
		Beans.putController("/result", new ResultController());
		Beans.putController("/main", new MainController());
		Beans.putController("/error", new ErrorController());
		
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// List ==========================================================================
		// controller 생성 -> 저장
		Beans.putController("/list", new ListController());
		
		//dao 생성 -> 저장
		Beans.putDAO("listDAO", new ListDAO());
		
		//service 생성 -> 저장
		Beans.putService("/list/list.do", new ListListService());
		Beans.putService("/list/delete.do", new ListDeleteService());
		Beans.putService("/list/view.do", new ListViewService());
		Beans.putService("/list/update.do", new ListUpdateService());
		Beans.putService("/list/register.do", new ListRegisterService());
		Beans.putService("/result/result.do", new TypeResultService());
		Beans.putService("/mbti/movieMBTI.html", new MBTIHitService());
		
		//service에 dao 넣기
		Beans.getService("/list/list.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/list/delete.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/list/view.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/list/update.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/list/register.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/result/result.do").setDAO(Beans.getDAO("listDAO"));
		Beans.getService("/mbti/movieMBTI.html").setDAO(Beans.getDAO("listDAO"));
		
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// Type ==========================================================================
		// controller 생성 -> 저장
		Beans.putController("/type", new TypeController());
		
		//dao 생성 -> 저장
		Beans.putDAO("typeDAO", new TypeDAO());
		
		//service 생성 -> 저장100
		Beans.putService("/type/list.do", new TypeListService());
		Beans.putService("/type/view.do", new TypeViewService());
		Beans.putService("/type/write.do", new TypeWriteService());
		Beans.putService("/type/update.do", new TypeUpdateFileService());
		Beans.putService("/type/delete.do", new TypeDeleteService());
		
		//service에 dao 넣기
		Beans.getService("/type/list.do").setDAO(Beans.getDAO("typeDAO"));
		Beans.getService("/type/view.do").setDAO(Beans.getDAO("typeDAO"));
		Beans.getService("/type/write.do").setDAO(Beans.getDAO("typeDAO"));
		Beans.getService("/type/update.do").setDAO(Beans.getDAO("typeDAO"));
		Beans.getService("/type/delete.do").setDAO(Beans.getDAO("typeDAO"));
		
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// Board ==========================================================================
		// controller 생성 -> 저장
		Beans.putController("/board", new BoardController());
		
		//dao 생성 -> 저장
		Beans.putDAO("boardDAO", new BoardDAO());
		Beans.putDAO("boardReplyDAO", new BoardReplyDAO());
		
		//service 생성 -> 저장
		Beans.putService("/board/list.do", new BoardListService());
		Beans.putService("/board/view.do", new BoardViewService());
		Beans.putService("/board/write.do", new BoardWriteService());
		Beans.putService("/board/update.do", new BoardUpdateService());
		Beans.putService("/board/delete.do", new BoardDeleteService());
				
		//reply
		Beans.putService("/board/replyList.do", new BoardReplyListService());
		Beans.putService("/board/replyWrite.do", new BoardReplyWriteService());
		Beans.putService("/board/replyUpdate.do", new BoardReplyUpdateService());
		Beans.putService("/board/replyDelete.do", new BoardReplyDeleteService());
		
		//service에 dao 넣기
		Beans.getService("/board/list.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.getService("/board/view.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.getService("/board/write.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.getService("/board/update.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.getService("/board/delete.do").setDAO(Beans.getDAO("boardDAO"));
		
		//reply
		Beans.getService("/board/replyList.do").setDAO(Beans.getDAO("boardReplyDAO"));
		Beans.getService("/board/replyWrite.do").setDAO(Beans.getDAO("boardReplyDAO"));
		Beans.getService("/board/replyUpdate.do").setDAO(Beans.getDAO("boardReplyDAO"));
		Beans.getService("/board/replyDelete.do").setDAO(Beans.getDAO("boardReplyDAO"));
		
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// Notice ==========================================================================
		// Controller 생성 -> 저장
		Beans.putController("/notice", new NoticeController());
		
		// dao 생성 -> 저장
		Beans.putDAO("noticeDAO", new NoticeDAO());
		Beans.putDAO("noticeReplyDAO", new NoticeReplyDAO());
		
		
		// service 생성 -> 저장
		Beans.putService("/notice/list.do", new NoticeListService());
		Beans.putService("/notice/view.do", new NoticeViewService());
		Beans.putService("/notice/write.do", new NoticeWriteService());
		Beans.putService("/notice/delete.do", new NoticeDeleteService());
		
		// 댓글 service
		Beans.putService("/notice/replyList.do", new NoticeReplyListService());
		Beans.putService("/notice/replyWrite.do", new NoticeReplyWriteService());
		Beans.putService("/notice/replyUpdate.do", new NoticeReplyUpdateService());
		Beans.putService("/notice/replyDelete.do", new NoticeReplyDeleteService());
		
		//service에 dao 넣기
		Beans.getService("/notice/list.do").setDAO(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/view.do").setDAO(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/write.do").setDAO(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/delete.do").setDAO(Beans.getDAO("noticeDAO"));
		
		// 댓글 dao
		Beans.getService("/notice/replyList.do").setDAO(Beans.getDAO("noticeReplyDAO"));
		Beans.getService("/notice/replyWrite.do").setDAO(Beans.getDAO("noticeReplyDAO"));
		Beans.getService("/notice/replyUpdate.do").setDAO(Beans.getDAO("noticeReplyDAO"));
		Beans.getService("/notice/replyDelete.do").setDAO(Beans.getDAO("noticeReplyDAO"));
		 
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// FeedBack ==========================================================================
		Beans.putController("/feedback", new feedbackController());
		
		Beans.putDAO("feedbackDAO", new FeedbackDAO());
		
		Beans.putService("/feedback/adminList.do", new FeedbackAdminListService() );
		Beans.putService("/feedback/list.do", new FeedbackListService() );
		Beans.putService("/feedback/answer.do", new FeedbackAnswerService() );
		Beans.putService("/feedback/view.do", new FeedbackViewService() );
		Beans.putService("/feedback/write.do", new FeedbackWriteService() );
		Beans.putService("/feedback/delete.do", new FeedbackDeleteService() );
		
		Beans.getService("/feedback/adminList.do").setDAO(Beans.getDAO("feedbackDAO"));
		Beans.getService("/feedback/list.do").setDAO(Beans.getDAO("feedbackDAO"));
		Beans.getService("/feedback/answer.do").setDAO(Beans.getDAO("feedbackDAO"));
		Beans.getService("/feedback/view.do").setDAO(Beans.getDAO("feedbackDAO"));
		Beans.getService("/feedback/write.do").setDAO(Beans.getDAO("feedbackDAO"));
		Beans.getService("/feedback/delete.do").setDAO(Beans.getDAO("feedbackDAO"));
		 
		// Service, Controller, DAO를 저장할 때 오탈자 꼭 확인하고 Service는 꼭 DAO를 넣었는지 확인할 것!!!!
		// Member ==========================================================================
		// controller 생성 -> 저장
		Beans.putController("/member", new MemberController());
		
		//dao 생성 -> 저장
		Beans.putDAO("memberDAO", new MemberDAO());
		
		//service 생성 -> 저장
		Beans.putService("/member/list.do", new MemberListService());
		Beans.putService("/member/update.do", new MemberUpdateService());
		Beans.putService("/member/view.do", new MemberViewService());
		Beans.putService("/member/write.do", new MemberWriteService());
		Beans.putService("/member/login.do", new MemberLoginService());
		Beans.putService("/ajax/checkId.do", new MemberCheckIdService());
		Beans.putService("/member/gradeModify.do", new MemberGradeModifyService());
		Beans.putService("/member/delete.do", new MemberDeleteService());
		
		//service에 dao 넣기
		Beans.getService("/member/list.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/update.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/view.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/write.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/login.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/ajax/checkId.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/gradeModify.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.getService("/member/delete.do").setDAO(Beans.getDAO("memberDAO"));
		
		
		// 저장이 잘 되어 있는지 확인
		//System.out.println("Init.init().Beans.getCotroller(\"/feedback :  " + Beans.getController("/feedback"));
		System.out.println("Init.init().Beans.getService(\"/feedback/delete.do\" :  " + Beans.getService("/feedback/delete.do"));
		//System.out.println("Init.init().Beans.getDAO(\"/feedbackDAO\" :  " + Beans.getDAO("feedbackDAO"));
		
		try {
			
			Class.forName("com.mbti.util.db.DBInfo");			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new ServletException("드라이버를 확인하는 중 오류가 발생하였습니다. 드라이버를 확인해 주세요");
			
		}
		
	}

}
