# Lift VDOM Chat

This project is the "Ubiquitous Chat App" for Lift, but with server-side virtual DOM diffing.
The heavy-lifting (pun!) is done by Lift in [this branch](https://github.com/lift/framework/tree/joe-vdom).
Switch to that branch and do a `publishLocal` then run this project as usual with `container:start`.

Also note that this approach will support progressive enhancement.
That is, it will work even if scripting is disabled on the browser.
If scripting *is* enabled, then chat messages are sent and received asynchronously for an enhanced user experience.

See [Pakyow's Auto-Updating Views](https://pakyow.org/docs/live-views) for the inspiration of the idea to calculate DOM changes on the server.
