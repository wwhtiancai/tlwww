package com.tmri.rfid.company.ctrl;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tmri.rfid.company.bean.CpyRfidApplyDoc;
import com.tmri.rfid.company.service.CpyQueryManager;
import com.tmri.rfid.framework.ctrl.SystemCtrl;
import com.tmri.rfid.framework.util.StringUtil;
@Controller
public class CpyApplyDocPicCtrl extends SystemCtrl{
	@Autowired
	private CpyQueryManager cpyQueryManager;
	private static final byte[] noimage=Base64.decodeBase64("iVBORw0KGgoAAAANSUhEUgAAAJEAAABzCAMAAACvihCYAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAACoUExURQAAAL6+vrq6uszMzN3d3cHBwdnZ2cjIyMfHx8LCws7Ozru7u7+/v8TExMPDw9vb28nJycXFxbm5ubi4uM3NzcDAwNTU1Le3t8/Pz8bGxr29vdra2t7e3ra2ttPT09jY2NLS0srKytzc3Ly8vNXV1dfX19bW1svLy9HR0dDQ0LS0tLW1tbm5ubi4uLe3t7a2tru7u8DAwLq6urS0tLW1tby8vLOzs7KysnVyag4AAAAsdFJOUwC8028LpiGFhaZkyLGbmxZ6kNPeZLFD3lmQvCEL6UMsTnoWyDcsN29OWfTpK6fMfQAABIFJREFUeNrtm9mWqjoQQBEHEBmUQRRt5x7uPangab33/P+fHRRs6DaBhMHwYD30i0vX7mQnVCWFJMUxU8zgKC6CodrdSZlYD19OIQZxgcPzOdikQB8jjMQHdsY3IM0GaAERAn8eA21GIWpHhPb2SqS3BSiaOHkdAW3tZMpwKC5wzABgRkT7xGo4/hYX/yWjAs6rtLZjIvD+6S17gmJ5MO2YIpxJb+hKBParJDQ6EBO5kovgOoG6WCBpcuVAeC6pCZEpmGicEPUlJSEaCiZSEqLhk+hJ9CRqA9Hr1hx6njne7NpBtJoE+HSO8k189MYD8UTW3E6TBBzKXdFEmwDD9yxYFUs083/m3uAMRRLt9PN9yum/CyQyMKE6AWcljGgjE6uBkyeMSCcXcOC8CyJ6syk17lkXRLRFlBo33A/EEHm0ohsfP8QQdahEn5w797ImooBGBIhv43ZHbi1EO5lONOYB0kbhSGt2jPBxxgWEAbMiFZlNQcL7KTvQLAJCEdKsBiKVRhQGHEAyxL8sz6oTrWwK0bnPA3STjwmp4CnSB8qBk8buUPoTwOJSAdGUfFR6Njmkzv4nDEjF2Qiqko1cVln2iwxIRUTLzv+E090un9QZpGKXCrPa6d2PYllhHSHCDgtFChZn/mvTzow8YLx3S0iNmPVmqdfm8uV+4PJZFH6wKiU1s95MFeRA3Tshvhwq28OVVE5qZr1Z6/63rjrfdrUB705NRMrVu6mTCE3Ou2HJ07shIkXOv/LJ0bsZIsMuuoPC1BTuJxGq4zzb8Auv6eh63xN1qgO9MNzSAc2llGheF5HxwnivR0ZKiUyoh8jwma8aZTeXSK1njAwHOO713DyicS1Ehs9x00vUOyUy6pg1Jqmzettasx6xSp2nd5nVb6kW+QPTR4gfya1MZOm436sqNV1v/lmzPIxh2Kso9Te9XTLRhI1op4eXArW/rCg1VW/e1W9dgBABiV/qbG0zLb1DLm59EwBer6rUmZPWblmPIqm/SmbI6q3YlZpyTu8ln7QL/VtVkuo9sav15JDHqNijxKEU6TZKpaXOJyocox9AF6TYpSpSV/Focd8MFOm95Ek/OIkK1pqlE8qvKCt3lRo6zcp4tNCJ9SDA52cNDVBkotxs5M6hDJMQIjpQPUEmynn2L5rucOMlojjUOBF19TcPRCGiZSNWJ2y8bZNr9S8eAMS1Qy4e0rbJ4dEDHOJ70j4IiMOjRzjENUabfx/UHM3s0TQUSkRYa4KJxq0jMuBJVOLZ/yRq/eqfwHP1l9ghV6cQPyT+bBk9Ovjos5mQv8cvlzUbeR00E+ulZEnW15/docET9jp7tCatI6rrpqY+IrV1Y9Q+jwx4ekSO9C2f26wFgokSn7EhaQnR9X0/geF9vS12QMl5Yn8jkKenyPHRXahJloNv10v9R4c5SWLu3Q4T/UOm+RHww+Pnm5mxzm+jFr296lyzgbkPbSG69YoYTluGqHNb8OZLGyYOwk66A22D81nky/TX1+lH8+xd+WDSGSGBQOgY3LfKfSiqwNC+xucvzPWZuoK4IxoAAAAASUVORK5CYII=");
	@RequestMapping("/company/applydocpic.do")
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response,String sqbh,String lx) throws Exception{
		byte[] img=noimage;
		try{
			if(StringUtil.checkBN(sqbh)&&StringUtil.checkBN(lx)){
				CpyRfidApplyDoc doc=cpyQueryManager.getApplyDoc(sqbh,lx);
				if(doc==null||doc.getWd()==null||doc.getWd().length==0){
					img=noimage;
				}else{
					img=doc.getWd();
				}
			}
			OutputStream out=response.getOutputStream();
			out.write(img);
			out.flush();
			out.close();
		}catch(IOException ioe){
			System.out.println("[applyDoc.do.OutputStream]"+ioe.getMessage());
			ioe.printStackTrace();
		}catch(Exception ex){
			System.out.println("[applyDoc.do]"+ex.getMessage());
			img=noimage;
		}
		return null;
	}
}
