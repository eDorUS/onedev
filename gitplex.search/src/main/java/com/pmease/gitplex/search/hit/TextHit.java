package com.pmease.gitplex.search.hit;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.pmease.commons.lang.TokenPosition;

public class TextHit extends QueryHit {

	private static final long serialVersionUID = 1L;
	
	private final String lineContent;
	
	public TextHit(String blobPath, String lineContent, TokenPosition hitPos) {
		super(blobPath, hitPos);
		
		this.lineContent = lineContent;
	}

	public String getLineContent() {
		return lineContent;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getTokenPos().getLine()).append(": ");
		TokenPosition.Range range = getTokenPos().getRange();
		builder.append(lineContent.substring(0, range.getStart()));
		builder.append("[").append(lineContent.substring(range.getStart(), range.getEnd())).append("]");
		builder.append(lineContent.substring(range.getEnd()));
		return builder.toString();
	}

	@Override
	public Component render(String componentId) {
		return new TextHitPanel(componentId, this);
	}

	@Override
	public ResourceReference getIcon() {
		return new PackageResourceReference(FileHit.class, "bullet.gif");
	}

	@Override
	public String getScope() {
		String fileName = getBlobPath();
		if (fileName.contains("/")) 
			fileName = StringUtils.substringAfterLast(fileName, "/");
		return fileName;
	}

	@Override
	protected int score() {
		return lineContent.length();
	}

}
