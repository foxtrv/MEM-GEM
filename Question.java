/*************************************************************
* File: 	Question.java				     *
* Description:  Object class to store a question and answer. *
*************************************************************/

import java.util.*;
import java.io.*;

public class Question implements Serializable
{
	private String question;
	private String answer;
	private int numAttempted;
	private int numCorrect;
	private String category;

	public Question()
	{
		question = "";
		answer = "";
		numAttempted = 0;
		numCorrect = 0;
		category = "";
	}

	public Question(Question q)
	{
		question = q.getQuestion();
		answer = q.getAnswer();
		numAttempted = q.getAttempted();
		numCorrect = q.getCorrect();
		category = q.getCategory();
	}

	public void addAttempt() 	  { numAttempted++; 			}
	public void addCorrect() 	  { numCorrect++; 			}
	public void setQuestion(String Q) { question = Q; 			}
	public void setAnswer(String A)   { answer = A; 			}
	public void setCategory(String C) { category = C; 			}
	public int getAttempted() 	  { return numAttempted; 		}
	public int getCorrect() 	  { return numCorrect; 			}
	public int getIncorrect() 	  { return numAttempted - numCorrect; 	}
	public String getQuestion() 	  { return question; 			}
	public String getAnswer() 	  { return answer; 			}
	public String getCategory() 	  { return category; 			}
};
