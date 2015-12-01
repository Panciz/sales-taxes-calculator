package org.dpoletti.sales_taxes_calc.catalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.dpoletti.sales_taxes_calc.model.ItemType;

/**
 * 
 * Simple implementation of the catalog based on properties file
 * 
 * @author dapol
 *
 */
public class FileProductCatalog implements ProductCatalog {

	
	private Properties catalogFile;
	private String fileName;
	
	public FileProductCatalog(String fileName) {
		this.fileName=fileName;
	}
	
	public void initialize() throws Exception{
		this.loadFile();
	}
	
	private void loadFile() throws Exception{
		File file=new File(fileName);
		if(!file.exists() || !file.canRead()){
			throw new Exception("Cannot read product catalog: file "+fileName+" do not exists or is not readeable");
		}
		
		catalogFile=new Properties();
		try(InputStream is= new FileInputStream(file)){
			catalogFile.load(is);
		}catch (IOException e) {
			throw new Exception("Error reading catalog file: "+e.getMessage());
		}
	}
	
	
	@Override
	public ItemType getItemType(String itemName)
	{
		if(this.catalogFile==null){
			throw new RuntimeException("File Catalog must be initialezed");
		}
		if(itemName==null)
			return ItemType.fromString(ItemType.OTHER.toString());
		String type = catalogFile.getProperty(itemName.toLowerCase(),ItemType.OTHER.toString());
		return ItemType.fromString(type);
	}

}
