package com.web.root.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.root.customer.dto.BookmarkDTO;
import com.web.root.customer.dto.CartDTO;
import com.web.root.customer.dto.CustomerHelpReplyDTO;
import com.web.root.customer.dto.ParcelDTO;
import com.web.root.customer.dto.PurchaseDTO;
import com.web.root.member.dto.MemberDTO;
import com.web.root.mybatis.customer.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	public CustomerMapper customerMapper;

	static final String URL = "http://info.sweettracker.co.kr/";
	
	// 회원정보
	@Override
	public MemberDTO memberInfo(Map<String, Object> map) {
		MemberDTO memberDTO = customerMapper.memberInfo(Integer.parseInt(map.get("MemberSeq").toString()));
		return memberDTO; 
	}

	// 회원정보 수정
	@Override
	public int memberUpdate(Map<String, Object> map) {
		try {
			if(map.get("memberPw")!=null&map.get("memberNickname")!=null&map.get("memberName")!=null&map.get("memberPhone")!=null&map.get("memberAddr")!=null) {
				return customerMapper.memberUpdate(map);
			} else return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// 회원탈퇴
	@Override
	public int memberDelete(int MemberSeq) {
		try {
			return customerMapper.memberDelete(MemberSeq);
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	//장바구니
	@Override
    public List<CartDTO> cartList(int memberSeq) {
       return customerMapper.cartList(memberSeq);
    }

	// 장바구니 삭제
	@Override
	public int cartDelect(Map<String, String> map) {
		int memberSeq = Integer.parseInt(map.get("memberSeq"));
		int postSeq = Integer.parseInt(map.get("postSeq"));
		return customerMapper.cartDelect(memberSeq, postSeq);
	}

	// 구매내역
	@Override
	public List<PurchaseDTO> purchaseProduct(int memberSeq) {
		List<PurchaseDTO> purchaseList = customerMapper.purchaseProduct(memberSeq);
		return purchaseList;
	}
	
   //배송조회
   @Override
   public String parcelSelect(int purSeq) {
      ParcelDTO parcelDTO = new ParcelDTO(); 
      try {
         PurchaseDTO purchaseDTO = customerMapper.parcelSelect(purSeq);
         parcelDTO.setPurDscode(purchaseDTO.getPurDscode());
         parcelDTO.setPurCode(purchaseDTO.getPurCompany());
         parcelDTO.setPurAddr("http://info.sweettracker.co.kr//tracking/2");
         parcelDTO.setPurKey("n6UAfOSjfAtjoomyjlkscA");
         
         String response = URL+"/tracking/2?"+"t_key="+parcelDTO.getPurKey()+"&t_code="+parcelDTO.getPurCode()+"&t_invoice="+parcelDTO.getPurDscode();
           
           return response;
      }catch (Exception e) {
         e.printStackTrace();
         return null;
      }
      
   }

	// 즐겨찾기
	@Override
	public List<BookmarkDTO> bookmarkArtistList(int memberSeq) {
		return customerMapper.bookmarkArtistList(memberSeq);
	}

	// 나의문의 및 답변
	@Override
	public List<CustomerHelpReplyDTO> customerHelpReplyList(Map<String, Object> map) {
		int memberSeq = Integer.parseInt(map.get("memberSeq").toString());
		int helpSeq = Integer.parseInt(map.get("helpSeq").toString());
		List<CustomerHelpReplyDTO> list = customerMapper.customerHelpReplyList(memberSeq, helpSeq);
		return list;
	}
	
}
