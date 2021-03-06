package io.onedev.server.web.page.layout;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.onedev.server.web.component.link.ViewStateAwarePageLink;
import io.onedev.server.web.page.pullrequests.PullRequestListPage;

public class PullRequestListTab implements MainTab {

	private static final long serialVersionUID = 1L;

	@Override
	public Component render(String componentId) {
		return new ViewStateAwarePageLink<Void>(componentId, 
				PullRequestListPage.class, PullRequestListPage.paramsOf(0)) {

			private static final long serialVersionUID = 1L;

			@Override
			public IModel<?> getBody() {
				return Model.of("Pull Requests");
			}
			
		};
	}

	@Override
	public boolean isAuthorized() {
		return true;
	}

	@Override
	public boolean isActive(LayoutPage page) {
		return page instanceof PullRequestListPage; 
	}

}
