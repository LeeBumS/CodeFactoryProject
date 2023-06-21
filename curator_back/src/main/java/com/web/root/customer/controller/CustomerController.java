package com.web.root.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.web.root.customer.dto.BookmarkDTO;
import com.web.root.customer.dto.CartDTO;
import com.web.root.customer.dto.CustomerHelpReplyDTO;
import com.web.root.customer.dto.PurchaseDTO;
import com.web.root.customer.service.CustomerService;
import com.web.root.member.dto.MemberDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;

	// 회원정보
	@PostMapping(value="memberInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public MemberDTO memberInfo(@RequestBody Map<String, Object> map) {
		return customerService.memberInfo(map);
	}
	
	// 회원정보 수정
	@PutMapping(value="memberUpdate", produces = "application/json; charset=utf-8")
	@ResponseBody
	public int memberUpdate(@RequestBody Map<String, Object> map) {
		return customerService.memberUpdate(map);
	}
	
	// 회원 탈퇴
	@DeleteMapping(value="memberDelete", produces = "application/json; charset=utf-8")
	@ResponseBody
	public int memberDelete(@RequestParam("MemberSeq") int MemberSeq) {
		return customerService.memberDelete(MemberSeq);
	}
	
	// 장바구니
	@GetMapping(value="cartList", produces = "application/json; charset=utf8")
    @ResponseBody
    public List<CartDTO> cartList(@RequestParam("memberSeq") int memberSeq) {
       return customerService.cartList(memberSeq);
    }
	
	// 장바구니 삭제
	@PostMapping(value="cartDelete", produces = "application/json; charset=utf8")
	@ResponseBody
	public int cartDelect(@RequestBody Map<String, String> map) {
		return customerService.cartDelect(map);
	}
	
	// 구매내역
	@GetMapping(value="purchaseProduct", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<PurchaseDTO> purchaseProduct(@RequestParam("memberSeq") int memberSeq) {
		return customerService.purchaseProduct(memberSeq); 
	}
	
	// 배송조회
	   @GetMapping(value="parcelSelect", produces = "application/json; charset=utf-8")
	   @ResponseBody
	   public RedirectView parcelSelect(@RequestParam("purSeq") int purSeq) {
	      String url = customerService.parcelSelect(purSeq);
	      RedirectView redirectView = new RedirectView();
	      redirectView.setUrl(url);
	      return redirectView;
	   }
	
	
	
	// 즐겨찾기(북마크 등록한 작가)
	@GetMapping(value="bookmarkArtistList", produces = "application/json; charset=utf-8")
	@ResponseBody 
	public List<BookmarkDTO> bookmarkArtistList(@RequestParam("memberSeq") int memberSeq){
		return customerService.bookmarkArtistList(memberSeq);
	}
	
	// 나의문의 및 답변
	@GetMapping(value="customerHelpReplyList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<CustomerHelpReplyDTO> customerHelpReplyList(@RequestBody Map<String, Object> map){
		return customerService.customerHelpReplyList(map);
	}
	
}
