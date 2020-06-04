import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Create the Student and Priorities classes here.
 */
 
class Student{
 	 private int id = 0;
 	 private String name = "";
 	 private double cgpa = 0;
 	 Student(int id, String name, double cgpa){
 	 	 this.id = id; 
 	 	 this.name = name;
 	 	 this.cgpa = cgpa;
 	 }
 	 
 	 public int getID(){
 	 	 return this.id;
 	 }
 	 
 	 public double getCGPA(){
 	 	 return this.cgpa;
 	 }
 	 
 	 public String getName(){
 	 	 return this.name;
 	 }
}

class Triplet<T, U, V>{
	private final T name;
	private final U CGPA;
	private final V ID;
	
	Triplet(T first, U second, V third){
		this.name = first;
		this.CGPA = second;
		this.ID = third;
	}
	public T getName(){return this.name;}
	public U getCGPA(){return this.CGPA;}
	public V getID(){return this.ID;}
}

class Priorities{
	Priorities(){}
	static List<Student> student_list = new ArrayList<Student>();
	List<Student> getStudents(List<String> events){
		int curr_evnt = 0;
		while(curr_evnt < events.size()){
			//Adding students
			if(events.get(curr_evnt).contains("ENTER")){
				//Get name, ID and CGPA by substringing ENTER out and reading the string
				String data = events.get(curr_evnt).substring(5);
				Scanner scan = new Scanner(data);
				Triplet<String, Double, Integer> stud_data = new Triplet<>(scan.next(), scan.nextDouble(), scan.nextInt());
				//Initializing new student and adding him into the list
				Student stud = new Student(stud_data.getID(), stud_data.getName(), stud_data.getCGPA());
				student_list.add(stud);
			}
			else if(events.get(curr_evnt).contains("SERVED")){
				//Since list is unordered, use linear search to find the highest priority
				if(student_list.isEmpty()){
					//TODO: Not sure what to return
					return student_list;
				}
				else{
					Student best = student_list.get(0);//set placeholder for highest cpga
					int curr_best = 0;
					for(int i = 0; i < student_list.size(); i++){
						//If student has equal cgpa then compare names
						if(student_list.get(i).getCGPA() == student_list.get(curr_head).getCGPA()){
							if(student_list.get(i).getName().compareTo(student_list.get(curr_best).getName()) < 0) {
								curr_best = i; 
							}	
						}
						//If student has greater cgpa than make him top priority
						else if(student_list.get(i).getCGPA() > student_list.get(curr_best).getCGPA()){
							curr_best = i;
						}
					}
					//Remove Student top priority from list
					if(student_list.get(curr_best) != null){
						student_list.remove(curr_best);
					}
					else{
						//TODO: Not sure what to return
						return student_list;
					}	
				}
			}
			curr_evnt++;
		}
		//Check if list is empty
		if(student_list.isEmpty()){
			//TODO: Not sure what to return
			return student_list;
		}
		else{
			//Order leftover students in highest to lowest priority w bubble sort
			for(int i = 0; i < student_list.size(); i++){
				for(int j = 0; j < student_list.size(); j++){
					if(student_list.get(i).getCGPA() > student_list.get(j).getCGPA()){
						Student temp;
						temp = student_list.get(i);
						student_list.set(i, student_list.get(j));
						student_list.set(j, temp);
					}
					else if(student_list.get(i).getCGPA() == student_list.get(j).getCGPA()){
						if(student_list.get(i).getName().compareTo(student_list.get(j).getName()) < 0){
							Student temp;
							temp = student_list.get(i);
							student_list.set(i, student_list.get(j));
							student_list.set(j, temp);	
						}
					}
				}	
			}
			return student_list;
		}	
	}
}
/* PRE WRITTEN CODE */
public class java_priority_queue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
