# Lift Progressive Chat

This project is the "Ubiquitous Chat App" for Lift, but with progressive enhancement.
That is, it wil work even if scripting is disabled on the browser.
If scripting *is* enabled, then chat messages are sent and received asynchronously for an enhanced user experience.

The guiding principle of this project is to work out a convenient way of achieving this behavior with the Lift framework.
The ultimate goal is to develop a server-side re-render of pages upon change with a VDOM-style diff being returned/pushed to the client.

See [Pakyow's Auto-Updating Views](https://pakyow.org/docs/live-views) for the inspiration of the idea.