package com.ktdsuniversity.edu.generics.collections.airport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AirPlaneTest {
	
	public static void airSearch(Map<String, List<AirplaneInfo>> status, String search) {
		
		Scanner sc = new Scanner(System.in);
		String nn = "";
		int count = 0;
		
		if(status.get(search) != null && search != null) {
			List<AirplaneInfo> airplaneName = status.get(search);
			AirplaneInfo info;
			System.out.println(search + "편의 좌석 현황입니다. (O:예약 가능, X: 예약 불가능)");
			for(int i = 0; i < airplaneName.size(); i++) {
				info = airplaneName.get(i);
				
				System.out.print(info.getSeatNum() + ":" + info.getSeatStatus() + ", ");
				
				if(info.getSeatStatus().equals("X")) {
					count++;
				}
				if(count >= airplaneName.size()){
					System.out.println(" \n\n예약 가능한 좌석이 없습니다. 다른 비행기 편을 이용해 주세요.");
					return;
				}
				
			}
			System.out.println();
			
			System.out.print("좌석 예약을 하려면 번호를 입력하세요: ");
			nn = sc.nextLine();
			if(Integer.parseInt(nn) > airplaneName.size() || Integer.parseInt(nn) < 0) {
				throw new OverException("없는 좌석 번호 입니다.");
			}
			for(int i = 0; i < airplaneName.size(); i++) {
				info = airplaneName.get(i);
				if(info.getSeatNum() == Integer.parseInt(nn)) {
					System.out.print("\n" + info.getSeatNum() + "좌석을 예약하시겠습니까? (y/N) : ");
					nn = sc.nextLine();
					if(nn.equals(null) || !nn.equals("y") && !nn.equals("N")) {
						throw new BadChoiceException("없는 선택지입니다.");
					}
					if( info.getSeatStatus().equals("O")) {
						if(nn.equals("y")) {
							System.out.println("\n" + info.getSeatNum() + "번 좌석이 예약되었습니다.");
							info.setSeatStatus("X");
							return;
						}
						else {
							return;
						}
					}else {
						System.out.println("이미 예약된 죄석입니다.");
						return;
					}
				}
					
				
				
			}
		}
		else if (status.get(search) == null){
			throw new NullException(search + " 편은 존재하지 않습니다.");
		}
		
	}
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		List<AirplaneInfo> seatStatus = new ArrayList<>();
		List<AirplaneInfo> seatStatus1 = new ArrayList<>();
		List<AirplaneInfo> seatStatus2 = new ArrayList<>();
		Map<String, List<AirplaneInfo>> airplaneName = new HashMap<>();
		
		//0002편 4
		seatStatus.add(new AirplaneInfo(1, "O"));
		seatStatus.add(new AirplaneInfo(2, "O"));
		seatStatus.add(new AirplaneInfo(3, "X"));
		seatStatus.add(new AirplaneInfo(4, "X"));
		seatStatus.add(new AirplaneInfo(5, "X"));
		seatStatus.add(new AirplaneInfo(6, "X"));
		seatStatus.add(new AirplaneInfo(7, "X"));
		seatStatus.add(new AirplaneInfo(8, "O"));
		seatStatus.add(new AirplaneInfo(9, "O"));
		
		//0003편 0
		seatStatus1.add(new AirplaneInfo(1, "X"));
		seatStatus1.add(new AirplaneInfo(2, "X"));
		seatStatus1.add(new AirplaneInfo(3, "X"));
		seatStatus1.add(new AirplaneInfo(4, "X"));
		seatStatus1.add(new AirplaneInfo(5, "X"));
		seatStatus1.add(new AirplaneInfo(6, "X"));
		seatStatus1.add(new AirplaneInfo(7, "X"));
		seatStatus1.add(new AirplaneInfo(8, "X"));
		seatStatus1.add(new AirplaneInfo(9, "O"));
		
		airplaneName.put("0002", seatStatus);
		airplaneName.put("0003", seatStatus1);
		
		String search = "";
		while(true) {
				System.out.print("\n비행기 의 이름을 입력하세요 : ");
				search = sc.nextLine();
				
			try {
				airSearch(airplaneName, search);
			}
			catch(NullException | OverException | BadChoiceException e){
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
	}
	
	
}
