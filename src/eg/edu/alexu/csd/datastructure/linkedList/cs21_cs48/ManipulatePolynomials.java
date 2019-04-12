package eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;
//import static java.lang.Math.pow;
public class ManipulatePolynomials implements IPolynomialSolver{
	
	private  SingleLinkedList A = new SingleLinkedList();
	private SingleLinkedList B = new SingleLinkedList();
	private SingleLinkedList C = new SingleLinkedList();
	private SingleLinkedList R = new SingleLinkedList();
	
	@Override
	public void setPolynomial(char poly, int[][] terms) {
		// TODO Auto-generated method stub
		int [][]arr =sortPoly(terms);
		clearPolynomial(poly);
		for(int i=0;i<arr.length;i++){
			PolynomialNode term =new PolynomialNode(arr[i][0],arr[i][1]);
			if(poly=='A'){
				A.add(term);
			}
			else if(poly=='B'){
				B.add(term);
			}
			else if(poly=='C'){
				C.add(term);
			}
			else if(poly=='R'){
				R.add(term);
			}
			else{
				throw new RuntimeException();
			}
		}
		
		
	}

	@Override
	public String print(char poly) {
		// TODO Auto-generated method stub
		SingleLinkedList variable =getPoly(poly);
		PolynomialNode term;
		String res="" ;
		for(int i=0;i<variable.size;i++){
			term=(PolynomialNode) variable.get(i);
			if((int)term.coff==0||((int)term.exp==0&&(int)term.coff==0)){
				continue;
			}
			if((int)term.coff < 0 || i==0){
				res +=term.coff;
			}
			else if(i!=0){
				res +='+';
				res +=term.coff;
			}
			if((int)term.exp != 0){
			res +=poly ;}
			if((int)term.exp != 1&&(int)term.exp != 0){
			res +='^';
			res +=term.exp;}
		}
		
		
		return res;
	}

	@Override
	public void clearPolynomial(char poly) {
		// TODO Auto-generated method stub
		if(poly=='A'){
			A.clear();
		}else if (poly=='B'){
			B.clear();
		}else if (poly=='C'){
			C.clear();
		}else if (poly=='R'){
			R.clear();
		}else {
			throw new RuntimeException();
		}
		
	}

	@Override
	public float evaluatePolynomial(char poly, float value) {
		// TODO Auto-generated method stub
		SingleLinkedList variable =getPoly(poly);
		PolynomialNode term;
		float sum=0;
		for(int i=0;i<variable.size;i++){
			term =(PolynomialNode) variable.get(i);
			sum += (int)term.coff*Math.pow(value, (int) term.exp);
			
		}
		return sum;
	}

	@Override
	public int[][] add(char poly1, char poly2) {
		// TODO Auto-generated method stub
		SingleLinkedList variable1 =getPoly(poly1);
		SingleLinkedList variable2 =getPoly(poly2);
		if(variable1==null||variable2==null){
			throw new RuntimeException();
		}
		PolynomialNode term1;
		PolynomialNode term2;
		int size=variable1.size+variable2.size;
		int [][]arr = new int[size][2];
	int i=0,j=0;
		while(i<variable1.size){
				term1 =(PolynomialNode) variable1.get(i);
				arr[i][0]=(int)term1.coff;
				arr[i][1]=(int)term1.exp;
				i++;
			}
		while(j<variable2.size){
			
				term2 =(PolynomialNode) variable2.get(j);
				arr[i+j][0]=(int)term2.coff;
				arr[i+j][1]=(int)term2.exp;
			
			j++;
		}
		
		 setPolynomial('R',sortPoly(arr));
		return sortPoly(arr);
	}

	@Override
	public int[][] subtract(char poly1, char poly2) {
		// TODO Auto-generated method stub
		SingleLinkedList variable1 =getPoly(poly1);
		SingleLinkedList variable2 =getPoly(poly2);
		if(variable1==null||variable2==null){
			throw new RuntimeException();
		}
		PolynomialNode term1;
		PolynomialNode term2;
		int size=variable1.size+variable2.size;
		int [][]arr = new int[size][2];
	int i=0,j=0;
		while(i<variable1.size){
				term1 =(PolynomialNode) variable1.get(i);
				arr[i][0]=(int)term1.coff;
				arr[i][1]=(int)term1.exp;
				i++;
			}
		while(j<variable2.size){
			
				term2 =(PolynomialNode) variable2.get(j);
				arr[i+j][0]=-1*(int)term2.coff;
				arr[i+j][1]=(int)term2.exp;
			
			j++;
		}
		
		setPolynomial('R',sortPoly(arr));
		return sortPoly(arr);
	}

	@Override
	public int[][] multiply(char poly1, char poly2) {
		// TODO Auto-generated method stub
		SingleLinkedList variable1 =getPoly(poly1);
		SingleLinkedList variable2 =getPoly(poly2);
		if(variable1==null||variable2==null||variable1.isEmpty()||variable2.isEmpty()){
			throw new RuntimeException();
		}
		PolynomialNode term1;
		PolynomialNode term2;
		int [][]arr=new int[variable1.size*variable2.size][2];
		int i=0,j=0,s=0 ;
		for(i=0;i<variable1.size;i++){
			term1 =(PolynomialNode) variable1.get(i);
			for(j=0;j<variable2.size;j++){
				term2 =(PolynomialNode) variable2.get(j);
				arr[s][0]=(int)term1.coff*(int)term2.coff;
				arr[s][1]=(int)term1.exp+(int)term2.exp;
				s++;
			}
		}
		setPolynomial('R',sortPoly(arr));
		int arr2[][]=sortPoly(arr);
		System.out.println(print('R'));
		return 	arr2;
	}
	
	public int[][] sortPoly(final int[][] terms){
		int num=terms.length;
		for(int i=0;i<terms.length;i++){
			for(int j=i+1;j<terms.length;j++){
				if(terms[i][1]<terms[j][1]){
					int coeff =terms[i][0];
					int exp =terms[i][1];
					terms[i][0]=terms[j][0];
					terms[i][1]=terms[j][1];
					terms[j][0]=coeff;
					terms[j][1]=exp;
				}
			}
		}
	  for(int i=0;i<terms.length;i++){
		  for(int j=i+1;j<terms.length;j++){
				if(terms[i][1]==terms[j][1]&&(terms[j][0]!=0&&terms[j][1]!=0)){
					terms[i][0]=terms[i][0]+terms[j][0];
					terms[j][0]=0;
					terms[j][1]=0;
					num--;
				}
		  }
	  }
	  int [][] sortedTerms=new int[num][2];
	  int j=0;
	  for(int i=0;i<terms.length;i++){
			  if((terms[i][0]==0&&terms[i][1]==0)||terms[i][0]==0){
				  continue ;
			  }else{
				  sortedTerms[j][0]=terms[i][0];
				  sortedTerms[j][1]=terms[i][1];
				  j++;
			  }
			
			
	  }
	  
	 
		return sortedTerms ;
	}
	public SingleLinkedList getPoly(char poly){
		if(poly=='A'){
			return A ;
		}else if (poly=='B'){
			return B ;
		}else if (poly=='C'){
			return C ;
		}else if (poly=='R'){
			return R ;
		}else
		{
			throw new RuntimeException();
		}
		
	}

}
