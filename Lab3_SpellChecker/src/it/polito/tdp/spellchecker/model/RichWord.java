package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String word;
	private boolean corretta;

	public RichWord(String word) {
		super();
		this.word = word;
		corretta=false;
	}
	
	
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}


	public void isCorretta(){
		this.corretta=true;
	}

	public boolean correttaErrata(){
		return corretta;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	
}
