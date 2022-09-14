package coza.mphoti.microservicejpa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import coza.mphoti.microservicejpa.beans.Country;
import coza.mphoti.microservicejpa.controllers.AddResponse;

public class CountryService {

	static HashMap<Integer, Country> countryIdMAp;
	
	public CountryService()
	{
		countryIdMAp = new HashMap<Integer, Country>();
		
		Country southAfrica = new Country(1, "South Africa", "Pretoria");
		Country botswana = new Country(2, "Botswana", "Harare");
		Country namibia = new Country(3, "Namibia", "Fishhoek");
		
		countryIdMAp.put(1, southAfrica);
		countryIdMAp.put(2, botswana);
		countryIdMAp.put(3, namibia);
	}
	
	public List getAllCountries()
	{
		List countries=new ArrayList(countryIdMAp.values());
		return countries;
	}
	
	public Country getCountrybyID(int id) 
	{
		Country country = countryIdMAp.get(id);
		return country;
	}
	
	public Country getCountrybyName(String name) 
	{
		Country country = null;
		for(int i:countryIdMAp.keySet())
		{
			if(countryIdMAp.get(i).getCountryName().equals(name))
			{
				country = countryIdMAp.get(i);
			}
		}
		return country;
	} 
	
	public Country addCountry(Country country)
	{
		country.setId(getAvailId());
		countryIdMAp.put(country.getId(), country);
		return country;
		
	}
	
	//This method looks for the next available ID to assign a new country
	public static int getAvailId()
	{
		int max=0;
		for (int id:countryIdMAp.keySet())
		{
			if(max<=id)
				max=id;
		}
		return max+1;
	}
	
	public Country updateCountry(Country country)
	{
		if(country.getId()>0)
		{
			countryIdMAp.put(country.getId(), country);
		}
		return country;
	}
	
	
	public AddResponse deleteCountry(int id)
	{
		countryIdMAp.remove(id);
		AddResponse res = new AddResponse();
		res.setMessage("Country deleted...");
		res.setId(id);
		return res;
	}
}
